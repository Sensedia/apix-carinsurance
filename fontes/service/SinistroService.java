package com.sensedia.apix.server.service;

import java.util.List;

import com.sensedia.apix.server.dao.SinistroDAO;
import com.sensedia.apix.server.model.SinistroBean;

/**
 * Treinamento Gestão de APIs - APIX
 * 
 * @author Sensedia
 *
 */
public class SinistroService {
	
	SinistroDAO sinistroDAO = new SinistroDAO();

	public List<SinistroBean> listSinistro(
			Integer offset, Integer limit) {
		return sinistroDAO.listSinistro(null, offset, limit);
	}

	public SinistroBean getSinistro(Long id) {
		return sinistroDAO.getSinistro(id);
	}


	public SinistroBean insertSinistro(SinistroBean sinistro) {
		return sinistroDAO.insertSinistro(sinistro);
	}


	public SinistroBean updateSinistro(SinistroBean sinistro) {
		return sinistroDAO.updateSinistro(sinistro);
	}


	public void removeSinistro(Long id) {
		sinistroDAO.removeSinistro(id);
	}

}
