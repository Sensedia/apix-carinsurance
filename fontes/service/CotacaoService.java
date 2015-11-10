package com.sensedia.apix.server.service;

import java.util.List;

import com.sensedia.apix.server.dao.CotacaoDAO;
import com.sensedia.apix.server.dao.SeguradoDAO;
import com.sensedia.apix.server.model.CotacaoBean;
import com.sensedia.apix.server.model.SeguradoBean;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class CotacaoService {

	CotacaoDAO cotacaoDAO = new CotacaoDAO(); 
	SeguradoDAO seguradoDAO = new SeguradoDAO();
	
	public List<CotacaoBean> listCotacao(Integer offset, Integer limit, Double valorMinimo, Double valorMaximo) {

		return cotacaoDAO.listCotacao(null, offset, limit, valorMinimo, valorMaximo);
		
	}

	public CotacaoBean getCotacao(Long id) {
		return cotacaoDAO.getCotacao(id);
	}

	public CotacaoBean insertCotacao(CotacaoBean cotacao) {
		return cotacaoDAO.insertCotacao(cotacao);
	}

	public void removeCotacao(Long id) {
		cotacaoDAO.removeCotacao(id);
	}
	
	/**
	 * Cria uma cotação a partir do segurado.
	 * 
	 * @param segurado
	 * @return {@link CotacaoBean}
	 */
	public CotacaoBean requestCotacao(SeguradoBean segurado) {
		
		segurado = seguradoDAO.insertSegurado(segurado);
		
		CotacaoBean cotacao = new CotacaoBean();
		cotacao.setId(segurado.getId());
		cotacao.setNome(segurado.getNome());
		cotacao.setValorApolice(Double.valueOf(1000D));
		cotacao.setValorFranquia(Double.valueOf(200D));
		
		return cotacaoDAO.insertCotacao(cotacao);
		
	}
	
	/**
	 * Atualiza os valores da cotação e do segurado relacionado.
	 * 
	 * @param segurado
	 * @return {@link CotacaoBean}
	 */
	public CotacaoBean updateCotacao(SeguradoBean segurado) {
		
		segurado = seguradoDAO.updateSegurado(segurado);
		
		CotacaoBean cotacao = cotacaoDAO.getCotacao(segurado.getId());
		
		CotacaoBean c;
		if (cotacao == null) {
			
			c = new CotacaoBean();
			c.setId(segurado.getId());
			c.setNome(segurado.getNome());
			c.setValorApolice(Double.valueOf(1000D));
			c.setValorFranquia(Double.valueOf(200D));
			
			cotacaoDAO.insertCotacao(c);
			
		} else {
			
			c = new CotacaoBean();
			c.setId(segurado.getId());
			c.setNome(segurado.getNome());
			c.setValorApolice(Double.valueOf(cotacao.getValorApolice().doubleValue() + 100D));
			c.setValorFranquia(Double.valueOf(cotacao.getValorFranquia().doubleValue() + 10D));
			
			cotacaoDAO.updateCotacao(c);
			
		}
		
		return c;
		
	}	

}
