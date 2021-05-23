package br.com.igti.edugame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.igti.edugame.domain.Avatar;
import br.com.igti.edugame.domain.Equipamento;
import br.com.igti.edugame.domain.Inventario;
import br.com.igti.edugame.domain.InventarioId;
import br.com.igti.edugame.dto.AvatarComInventarioDTO;
import br.com.igti.edugame.dto.AvatarVestidoDTO;
import br.com.igti.edugame.enums.SlotsEnum;
import br.com.igti.edugame.repository.AvatarRepository;
import br.com.igti.edugame.repository.EquipamentoRepository;
import br.com.igti.edugame.repository.InventarioRepository;
import javassist.NotFoundException;

@Service
public class AvatarService {

	@Autowired
	private AvatarRepository avatarRepository;
	
	@Autowired
	EquipamentoRepository equipamentoRepository;
	
	@Autowired
	InventarioRepository inventarioRepository;
	
	public void comprarItem(Long avatarId, Long equipamentoId) {
		Avatar avatar = avatarRepository.findById(avatarId).get();
		Equipamento equipamento= equipamentoRepository.findById(equipamentoId).get();
		
		if (avatar.getOuro().compareTo(equipamento.getPreco()) >= 0) {
			avatar.diminuirOuro(equipamento.getPreco());
			InventarioId inventarioId = new InventarioId(avatarId, equipamentoId);
					
			Optional<Inventario> inventario = inventarioRepository.findById(inventarioId);
			if (inventario.isPresent()) {
				inventario.get().adicionarQuantidade(1);
				
				inventarioRepository.save(inventario.get());
			}else {
				Inventario novoInventario = new Inventario();
				novoInventario.setInventarioId(new InventarioId(avatarId, equipamentoId));
				novoInventario.setIsEquipado(false);
				novoInventario.setQuantidade(1);
				
				inventarioRepository.save(novoInventario);
			}
			avatarRepository.save(avatar);
		}else {
			throw new RuntimeException("Infelizmente você não possui ouro o bastante para este item");
		}
	}
	
	public void equiparItem(Long avatarId, Long equipamentoId) throws NotFoundException {
		
		// Buscar lista de items já equipados
		List<Inventario> itensEquipados = inventarioRepository.findAllEquippedById(avatarId);
		
		// Buscar o inventário referente ao item escolhido, e o equipamento escolhido
		Optional<Inventario> inventario = inventarioRepository.findById(new InventarioId(avatarId, equipamentoId));
		Optional<Equipamento> equipamento = equipamentoRepository.findById(equipamentoId);
		
		// Equipar o item
		if (inventario.isPresent() && inventario.get().getQuantidade() > 0) {
			inventario.get().setIsEquipado(true);
			inventarioRepository.save(inventario.get());
		}else {
			throw new NotFoundException("Você não possui o item");
		}
		
		// Desequipar os items do mesmo slot que estejam equipados
		for (Inventario itemEquipado : itensEquipados) {
			Optional<Equipamento> equipamentoAtual = equipamentoRepository.findById(itemEquipado.getInventarioId().getEquipamentoId());
			if (equipamentoAtual.isPresent() && equipamentoAtual.get().getSlot().equals(equipamento.get().getSlot())) {
				itemEquipado.setIsEquipado(false);
				inventarioRepository.save(itemEquipado);
			}
		}

	}
	
	public AvatarComInventarioDTO salvarAvatar(AvatarComInventarioDTO avatarComInventarioDTO) throws NotFoundException {
		equiparItem(avatarComInventarioDTO.getAvatarVestido().getAvatar().getId(), avatarComInventarioDTO.getAvatarVestido().getCorpo());
		equiparItem(avatarComInventarioDTO.getAvatarVestido().getAvatar().getId(), avatarComInventarioDTO.getAvatarVestido().getCabelo());
		equiparItem(avatarComInventarioDTO.getAvatarVestido().getAvatar().getId(), avatarComInventarioDTO.getAvatarVestido().getSapato());
		return avatarComInventarioDTO;
	}
	
	public AvatarComInventarioDTO detalharAvatar(Long avatarId) {
		Avatar avatar = avatarRepository.findById(avatarId).get();
		List<Inventario> itensEquipados = inventarioRepository.findAllEquippedById(avatarId);
		AvatarVestidoDTO dto = new AvatarVestidoDTO(avatar);
		
		for (Inventario inventario : itensEquipados) {
			Optional<Equipamento> equip = equipamentoRepository.findById(inventario.getInventarioId().getEquipamentoId());
			
			if (equip.isPresent()) {
				
				SlotsEnum slot = SlotsEnum.findByName(equip.get().slot);
				switch(slot) {
				  case Cabelo:
				    dto.setCabelo(equip.get().id);
				    break;
				  case Corpo:
					  dto.setCorpo(equip.get().id);
				    break;
				  case Sapato:
					  dto.setSapato(equip.get().id);
					  break;
				}
			}
		}
		
		List<Inventario> itens = inventarioRepository.findAllByAvatarId(avatarId);
		List<Long> equipamentos = new ArrayList<>();
		for (Inventario item : itens) {
			Optional<Equipamento> equipamento = equipamentoRepository.findById(item.getInventarioId().getEquipamentoId());
			if (equipamento.isPresent()) {
				equipamentos.add(equipamento.get().getId());
			}
		}
		
		AvatarComInventarioDTO dtoComInventario = new AvatarComInventarioDTO();
		dtoComInventario.setAvatarVestido(dto);
		dtoComInventario.setItens(equipamentos);
		
		return dtoComInventario;
	}
	
	public List<Avatar> buscarAvataresPorTurma(Long idTurma){
		return null;
	}
	
	public void criarAvatar() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Avatar avatar = new Avatar(auth.getName());
		
		avatarRepository.save(avatar);
	}

	public List<Avatar> listarAvatares() {
		return this.avatarRepository.findAll();
	}
}
