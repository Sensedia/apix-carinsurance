package com.sensedia.apix.server.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.sensedia.apix.server.model.SeguradoBean;

/**
 * 
 * Classe DAO para gerenciamento dos recursos relacionados
 * a Segurado.
 * 
 * @author Sensedia
 *
 */
@SuppressWarnings({ "unchecked", "unused" })
public class SeguradoDAO {

	/**
	 * Recupera a lista de segurados
	 * 
	 * @param offset
	 * @param limit
	 * @param cursorString
	 * @return {@link List}
	 */
	public List<SeguradoBean> listSegurado(Integer offset,
			Integer limit, String cursorString) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<SeguradoBean> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from SeguradoBean as Segurado");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(offset);
				query.setMaxResults(limit);
			}

			execute = (List<SeguradoBean>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Itera sobre os recursos encontrados para
			// carregamento em lazy fetch
			for (SeguradoBean obj : execute)
				;

		} finally {
			mgr.close();
		}

		return execute;
		
	}

	/**
	 * Recupera um segurado por ID
	 * 
	 * @param id
	 * @return {@link SeguradoBean}
	 */
	public SeguradoBean getSegurado(Long id) {
		EntityManager mgr = getEntityManager();
		SeguradoBean segurado = null;
		try {
			segurado = mgr.find(SeguradoBean.class, id);
		} finally {
			mgr.close();
		}
		return segurado;
	}

	/**
	 * Insere um segurado na base
	 * 
	 * @param segurado
	 * @return {@link SeguradoBean}
	 */
	public SeguradoBean insertSegurado(SeguradoBean segurado) {
		EntityManager mgr = getEntityManager();
		try {
			if (segurado.getId() != null && containsSegurado(segurado)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(segurado);
		} finally {
			mgr.close();
		}
		return segurado;
	}

	/**
	 * Atualiza um segurado
	 * 
	 * @param segurado
	 * @return {@link SeguradoBean}
	 */
	public SeguradoBean updateSegurado(SeguradoBean segurado) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsSegurado(segurado)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(segurado);
		} finally {
			mgr.close();
		}
		return segurado;
	}

	/**
	 * Remove um segurado da base
	 * 
	 * @param id
	 */
	public void removeSegurado(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			SeguradoBean segurado = mgr.find(SeguradoBean.class, id);
			mgr.remove(segurado);
		} finally {
			mgr.close();
		}
	}

	/**
	 * Verifica se o segurado existe
	 * 
	 * @param segurado
	 * @return boolean
	 */
	private boolean containsSegurado(SeguradoBean segurado) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			SeguradoBean item = mgr.find(SeguradoBean.class, segurado.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	/**
	 * Recupera o entity manager
	 * @return {@link EntityManager}
	 */
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
