package com.sensedia.apix.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
@Entity
public class CotacaoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Double valorApolice;
	private Double valorFranquia;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getValorApolice() {
		return valorApolice;
	}
	
	public void setValorApolice(Double valorApolice) {
		this.valorApolice = valorApolice;
	}
	
	public Double getValorFranquia() {
		return valorFranquia;
	}
	
	public void setValorFranquia(Double valorFranquia) {
		this.valorFranquia = valorFranquia;
	}

}
