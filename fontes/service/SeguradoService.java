package com.sensedia.apix.server.service;

import java.util.List;

import com.sensedia.apix.server.dao.SeguradoDAO;
import com.sensedia.apix.server.model.SeguradoBean;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class SeguradoService {

	SeguradoDAO seguradoDAO = new SeguradoDAO();
	
	public List<SeguradoBean> listSegurado(Integer offset,
			Integer limit) {
		return seguradoDAO.listSegurado(offset, limit, null);
	}

	public SeguradoBean getSegurado(Long id) {
		return seguradoDAO.getSegurado(id);
	}

	public SeguradoBean insertSegurado(SeguradoBean segurado) {
		return seguradoDAO.insertSegurado(segurado);		
	}

	public SeguradoBean updateSegurado(SeguradoBean segurado) {
		return seguradoDAO.updateSegurado(segurado);
	}

	public void removeSegurado(Long id) {
		seguradoDAO.removeSegurado(id);
	}

}
