package br.com.igti.edugame.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvatarCompraDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842215082891972538L;

	@JsonProperty("avatarId")
	Long avatarId;
	
	@JsonProperty("equipamentoId")
	Long equipamentoId;

	public Long getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Long avatarId) {
		this.avatarId = avatarId;
	}

	public Long getEquipamentoId() {
		return equipamentoId;
	}

	public void setEquipamentoId(Long equipamentoId) {
		this.equipamentoId = equipamentoId;
	}
}
