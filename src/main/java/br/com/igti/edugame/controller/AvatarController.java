package br.com.igti.edugame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.igti.edugame.domain.Avatar;
import br.com.igti.edugame.dto.AvatarComInventarioDTO;
import br.com.igti.edugame.dto.AvatarCompraDTO;
import br.com.igti.edugame.service.AvatarService;
import javassist.NotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AvatarController {
	
	@Autowired
	private AvatarService avatarService;
	
	@PreAuthorize("hasRole('ALUNO')")
	@GetMapping("/avatar/listar")
	public List<Avatar> listarAvatares() {
	    return avatarService.listarAvatares();
	}

	@PreAuthorize("hasRole('ALUNO')")
	@GetMapping("/avatar/detalhar/{avatarId}")
	public AvatarComInventarioDTO detalharAvatar(@PathVariable("avatarId") Long avatarId) {
	    return avatarService.detalharAvatar(avatarId);
	}
	
	@PreAuthorize("hasRole('ALUNO')")
	@PostMapping("/avatar/comprar")
	public void comprarItem(@RequestBody AvatarCompraDTO avatarCompraDTO) throws NotFoundException {
	    avatarService.comprarItem(avatarCompraDTO.getAvatarId(), avatarCompraDTO.getEquipamentoId());
	}
	
	@PostMapping("/avatar/salvar")
	public AvatarComInventarioDTO salvarAvatar(@RequestBody AvatarComInventarioDTO avatarComInventario) throws NotFoundException {
	    return avatarService.salvarAvatar(avatarComInventario);
	}
	
	
	
}
