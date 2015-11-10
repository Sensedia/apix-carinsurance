package com.sensedia.apix.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
@Entity
public class PerfilBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	private String lugarUso;
	private String estadoCivil;	
	private String estacionamento;
	private String sexo;
	private Integer idadeMenorCondutor;
	private Integer quilometragemDiaria;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
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
