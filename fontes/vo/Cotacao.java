package com.sensedia.apix.server.vo;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class Cotacao {

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
