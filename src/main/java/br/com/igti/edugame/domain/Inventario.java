package br.com.igti.edugame.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Inventario {
	
	@EmbeddedId
	private InventarioId inventarioId;
	
	private Boolean isEquipado;
	
	private Integer quantidade;
	
	public Boolean getIsEquipado() {
		return isEquipado;
	}
	public void setIsEquipado(Boolean isEquipado) {
		this.isEquipado = isEquipado;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public void adicionarQuantidade(int i) {
		this.quantidade++;
	}
	public InventarioId getInventarioId() {
		return inventarioId;
	}
	public void setInventarioId(InventarioId inventarioId) {
		this.inventarioId = inventarioId;
	}
	
	
}
