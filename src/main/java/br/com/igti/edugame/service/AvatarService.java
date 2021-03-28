package br.com.igti.edugame.service;

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
		
		Optional<Inventario> inventario = inventarioRepository.findById(new InventarioId(avatarId, equipamentoId));
		Optional<Equipamento> equipamento = equipamentoRepository.findById(equipamentoId);
		
		if (inventario.isPresent() && inventario.get().getQuantidade() > 0) {
			//Colocar os slots equipados no avatar em vez do inventário
			//Assim você não precisa percorrer todos os itens do cara desequipando tudo
			inventario.get().setIsEquipado(true);
			
			inventarioRepository.save(inventario.get());
		}else {
			throw new NotFoundException("Você não possui o item");
		}

	}
	
	public AvatarVestidoDTO detalharAvatar(Long avatarId) {
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
		
		return dto;
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
