package br.com.igti.edugame.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author dav-h
 *
 */
@Entity
public class Avatar implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5422070466112218731L;

	@Id
	@GeneratedValue
	public Long avatarId;
	
	public String name;	  
	  
	private BigDecimal ouro;
	
	public Long getId() {
		return avatarId;
	}

	public void setId(Long id) {
		this.avatarId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOuro() {
		return ouro;
	}

	public void adicionarOuro(BigDecimal ouro) {
		this.ouro = this.ouro.add(ouro);
	}
	
	public void diminuirOuro(BigDecimal ouro) {
		this.ouro = this.ouro.subtract(ouro);
	}

	public Avatar() {}
	
	public Avatar(String name) {
		this.name = name;
		this.ouro = BigDecimal.ZERO;
	}
}

