package com.sensedia.apix.server.vo;


/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class Sinistro {
	
	private Long id;
	
	private String tipo;
	private String localizacao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

}
