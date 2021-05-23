package br.com.igti.edugame.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.igti.edugame.domain.Avatar;

public class AvatarVestidoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233387979544736496L;

	public AvatarVestidoDTO(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public AvatarVestidoDTO() {
	}

	@JsonProperty("avatar")
	Avatar avatar;
	
	@JsonProperty("cabelo")
	Long cabelo;
	@JsonProperty("corpo")
	Long corpo;
	@JsonProperty("sapato")
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
