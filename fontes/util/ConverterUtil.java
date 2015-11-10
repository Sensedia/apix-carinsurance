package com.sensedia.apix.server.util;


import com.google.appengine.api.datastore.KeyFactory;
import com.sensedia.apix.server.model.CarroBean;
import com.sensedia.apix.server.model.CotacaoBean;
import com.sensedia.apix.server.model.PerfilBean;
import com.sensedia.apix.server.model.SeguradoBean;
import com.sensedia.apix.server.model.SinistroBean;
import com.sensedia.apix.server.vo.Carro;
import com.sensedia.apix.server.vo.Cotacao;
import com.sensedia.apix.server.vo.Perfil;
import com.sensedia.apix.server.vo.Segurado;
import com.sensedia.apix.server.vo.Sinistro;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class ConverterUtil {
	
	private ConverterUtil() {}
	
	private static ConverterUtil instace = null;
	
	/**
	 * Recupera um unica instancia da classe de conversão
	 * @return
	 */
	public static synchronized ConverterUtil getInstance() {
		if (instace == null) {
			instace = new ConverterUtil();			
		}
		return instace;	
	}
	
	/**
	 * Converte {@link SeguradoBean} para {@link Segurado} 
	 * 
	 * @param bean
	 * @return {@link Segurado}
	 */
	public Segurado toSeguradoVO(SeguradoBean bean) {
		
		Segurado vo = new Segurado();
		
		vo.setId(bean.getId());
		vo.setNome(bean.getNome());
		
		if (bean.getPerfil() != null) {
			vo.setPerfil(toPerfilVO(bean.getPerfil()));
		}
		
		if (bean.getCarro() != null) {
			vo.setCarro(toCarroVO(bean.getCarro()));
		}
		
		return vo;
		
	}
	
	/**
	 * Converte {@link PerfilBean} para {@link Perfil}
	 * 
	 * @param bean
	 * @return {@link Perfil}
	 */
	public Perfil toPerfilVO(PerfilBean bean) {
		
		Perfil vo = new Perfil();
		
		vo.setId(KeyFactory.keyToString(bean.getKey()));
		vo.setEstacionamento(bean.getEstacionamento());
		vo.setEstadoCivil(bean.getEstadoCivil());
		vo.setIdadeMenorCondutor(bean.getIdadeMenorCondutor());
		vo.setLugarUso(bean.getLugarUso());
		vo.setQuilometragemDiaria(bean.getQuilometragemDiaria());
		vo.setSexo(bean.getSexo());
		
		return vo;
		
	}
	
	/**
	 * Converte {@link CarroBean} para {@link Carro}
	 * 
	 * @param bean
	 * @return {@link Carro}
	 */
	public Carro toCarroVO(CarroBean bean) {
		
		Carro vo = new Carro();
		
		vo.setId(KeyFactory.keyToString(bean.getKey()));
		vo.setVersao(bean.getVersao());
		vo.setModelo(bean.getModelo());
		vo.setMarca(bean.getMarca());
		vo.setAno(bean.getAno());

		return vo;
		
	}	

	/**
	 * Converte {@link CotacaoBean} para {@link Cotacao}
	 * @param bean
	 * @return {@link Cotacao}
	 */
	public Cotacao toCotacaoVO(CotacaoBean bean) {
		
		Cotacao vo = new Cotacao();
		
		vo.setId(bean.getId());
		vo.setNome(bean.getNome());
		vo.setValorApolice(bean.getValorApolice());
		vo.setValorFranquia(bean.getValorFranquia());

		return vo;
		
	}
	
	/**
	 * Converte {@link SinistroBean} para {@link Sinistro}
	 * 
	 * @param bean
	 * @return {@link Sinistro}
	 */
	public Sinistro toSinistroVO(SinistroBean bean) {
		
		Sinistro vo = new Sinistro();
		
		vo.setId(bean.getId());
		vo.setLocalizacao(bean.getLocalizacao());
		vo.setTipo(Sinistro.Tipo.valueOf(bean.getTipo().toString()));

		return vo;
		
	}
	
	/**
	 * Converte {@link Segurado} para {@link SeguradoBean}
	 * 
	 * @param vo
	 * @return
	 */
	public SeguradoBean toSeguradoBean(Segurado vo) {
		
		SeguradoBean bean = new SeguradoBean();
		
		bean.setId(vo.getId());
		bean.setNome(vo.getNome());
		
		if (vo.getCarro() != null) {
			bean.setCarro(toCarroBean(vo.getCarro()));
		}

		if (vo.getPerfil() != null) {
			bean.setPerfil(toPerfilBean(vo.getPerfil()));
		}
		
		return bean;
		
	}
	
	/**
	 * Converte {@link Carro} para {@link CarroBean}
	 * 
	 * @param vo
	 * @return {@link CarroBean}
	 */
	public CarroBean toCarroBean(Carro vo) {
	
		CarroBean bean = new CarroBean();
		
		if (vo.getId() != null) {
			bean.setKey(KeyFactory.stringToKey(vo.getId()));
		}
		bean.setVersao(vo.getVersao());
		bean.setAno(vo.getAno());
		bean.setModelo(vo.getModelo());
		bean.setMarca(vo.getMarca());

		return bean;
		
	}
	
	/**
	 * Converte {@link Perfil} para {@link PerfilBean}
	 * 
	 * @param vo
	 * @return {@link PerfilBean}
	 */
	public PerfilBean toPerfilBean(Perfil vo) {
		
		PerfilBean bean = new PerfilBean();

		if (vo.getId() != null) {
			bean.setKey(KeyFactory.stringToKey(vo.getId()));
		}
		bean.setEstacionamento(vo.getEstacionamento());
		bean.setEstadoCivil(vo.getEstadoCivil());
		bean.setIdadeMenorCondutor(vo.getIdadeMenorCondutor());
		bean.setSexo(vo.getSexo());
		
		return bean;

	}
	
	/**
	 * Converte {@link Cotacao} para {@link CotacaoBean}
	 * 
	 * @param vo
	 * @return {@link CotacaoBean}
	 */
	public CotacaoBean toCotacaoBean(Cotacao vo) {
		
		CotacaoBean bean = new CotacaoBean();

		bean.setId(vo.getId());
		bean.setNome(vo.getNome());
		bean.setValorApolice(vo.getValorApolice());
		bean.setValorFranquia(vo.getValorFranquia());
		
		return bean;

	}

	/**
	 * Converte {@link Sinistro} to {@link SinistroBean}
	 * 
	 * @param vo
	 * @return {@link SinistroBean}
	 */
	public SinistroBean toSinistroBean(Sinistro vo) {
		
		SinistroBean bean = new SinistroBean();

		bean.setId(vo.getId());
		bean.setLocalizacao(vo.getLocalizacao());
		bean.setTipo(SinistroBean.Tipo.valueOf(vo.getTipo().toString()));
		
		return bean;

	}	

}
