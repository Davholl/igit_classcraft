package br.com.igti.edugame.dto;

import br.com.igti.edugame.domain.Avatar;

public class AvatarVestidoDTO {

	public AvatarVestidoDTO(Avatar avatar) {
		this.avatar = avatar;
	}

	Avatar avatar;
	
	Long cabelo;
	Long corpo;
	Long sapato;
	
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	public Long getCabelo() {
		return cabelo;
	}
	public void setCabelo(Long cabelo) {
		this.cabelo = cabelo;
	}
	public Long getCorpo() {
		return corpo;
	}
	public void setCorpo(Long corpo) {
		this.corpo = corpo;
	}
	public Long getSapato() {
		return sapato;
	}
	public void setSapato(Long sapato) {
		this.sapato = sapato;
	}
}
