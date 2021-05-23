package br.com.igti.edugame.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvatarComInventarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8373405767443350162L;

	@JsonProperty("avatarVestido")
	private AvatarVestidoDTO avatarVestido;
	
	@JsonProperty("itens")
	private List<Long> itens;

	public AvatarVestidoDTO getAvatarVestido() {
		return avatarVestido;
	}

	public void setAvatarVestido(AvatarVestidoDTO avatarVestido) {
		this.avatarVestido = avatarVestido;
	}

	public List<Long> getItens() {
		return itens;
	}

	public void setItens(List<Long> itens) {
		this.itens = itens;
	}
	
	
	

}
