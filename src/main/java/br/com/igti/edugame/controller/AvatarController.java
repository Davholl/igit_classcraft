package br.com.igti.edugame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igti.edugame.domain.Avatar;
import br.com.igti.edugame.service.AvatarService;
import javassist.NotFoundException;

@RestController
public class AvatarController {
	
	@Autowired
	private AvatarService avatarService;

	@PreAuthorize("hasRole('ALUNO')")
	@GetMapping("/avatar/detalhar/{avatarId}")
	public Avatar detalharAvatar(@PathVariable("avatarId") Long avatarId) {
	    return avatarService.detalharAvatar(avatarId);
	}
	
	@PreAuthorize("hasRole('ALUNO')")
	@PostMapping("/avatar/comprar")
	public void comprarItem(@RequestParam("avatarId") Long avatarId, @RequestParam("equipamentoId") Long equipamentoId) {
	    avatarService.comprarItem(avatarId, equipamentoId);
	}
	
	@PreAuthorize("hasRole('ALUNO')")
	@PostMapping("/avatar/equipar")
	public void equiparItem(@RequestParam("avatarId") Long avatarId,@RequestParam("equipamentoId") Long equipamentoId) throws NotFoundException {
	    avatarService.equiparItem(avatarId, equipamentoId);
	}
	
}
