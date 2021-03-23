package br.com.igti.edugame.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InventarioId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143158486329909866L;
	
	private Long avatarId;
    private Long equipamentoId;
    
    public InventarioId(Long avatarId, Long equipamentoId) {
		this.avatarId = avatarId;
		this.equipamentoId = equipamentoId;
	}
    
    public InventarioId() {}

	public Long getEquipamentoId() {
		return equipamentoId;
	}

	public Long getAvatarId() {
		return avatarId;
	}

}
