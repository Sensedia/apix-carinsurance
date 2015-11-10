package com.sensedia.apix.server.service;

import java.util.List;

import com.sensedia.apix.server.dao.CotacaoDAO;
import com.sensedia.apix.server.model.CotacaoBean;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class CotacaoService {

	CotacaoDAO cotacaoDAO = new CotacaoDAO(); 
	
	public List<CotacaoBean> listCotacao(Integer offset, Integer limit, Double valorMinimo, Double valorMaximo) {

		return cotacaoDAO.listCotacao(null, offset, limit, valorMinimo, valorMaximo);
		
	}

	public CotacaoBean getCotacao(Long id) {
		return cotacaoDAO.getCotacao(id);
	}

	public CotacaoBean insertCotacao(CotacaoBean cotacao) {
		return cotacaoDAO.insertCotacao(cotacao);
	}

	public CotacaoBean updateCotacao(CotacaoBean cotacao) {
		return cotacaoDAO.updateCotacao(cotacao);
	}

	public void removeCotacao(Long id) {
		cotacaoDAO.removeCotacao(id);
	}

}
