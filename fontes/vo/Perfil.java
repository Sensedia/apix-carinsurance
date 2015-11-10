package com.sensedia.apix.server.vo;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class Perfil {
	
	private String id;

	private String lugarUso;
	private String estadoCivil;	
	private String estacionamento;
	private String sexo;
	private Integer idadeMenorCondutor;
	private Integer quilometragemDiaria;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLugarUso() {
		return lugarUso;
	}
	
	public void setLugarUso(String lugarUso) {
		this.lugarUso = lugarUso;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getEstacionamento() {
		return estacionamento;
	}
	
	public void setEstacionamento(String estacionamento) {
		this.estacionamento = estacionamento;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Integer getIdadeMenorCondutor() {
		return idadeMenorCondutor;
	}
	
	public void setIdadeMenorCondutor(Integer idadeMenorCondutor) {
		this.idadeMenorCondutor = idadeMenorCondutor;
	}
	
	public Integer getQuilometragemDiaria() {
		return quilometragemDiaria;
	}
	
	public void setQuilometragemDiaria(Integer quilometragemDiaria) {
		this.quilometragemDiaria = quilometragemDiaria;
	}

}
