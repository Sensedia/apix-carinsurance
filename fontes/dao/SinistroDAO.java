package com.sensedia.apix.server.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.sensedia.apix.server.model.SinistroBean;

/**
 * 
 * Classe DAO para gerenciamento dos recursos relacionados
 * a sinistro.
 * 
 * @author Sensedia
 *
 */
public class SinistroDAO {

	/**
	 * Retorna a lista de sinistros.
	 * 
	 * @param cursorString
	 * @param offset
	 * @param limit
	 * @return {@link List}
	 * 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<SinistroBean> listSinistro(String cursorString,
			Integer offset, Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<SinistroBean> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from SinistroBean as Sinistro");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(offset);
				query.setMaxResults(limit);
			}

			execute = (List<SinistroBean>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Itera a lista de recursos para carregamento ativo.
			for (SinistroBean obj : execute);
			
		} finally {
			mgr.close();
		}

		return execute;
	}


	/**
	 * Recupera um sinistro por ID
	 * 
	 * @param id
	 * @return {@link SinistroBean}
	 * 
	 */
	public SinistroBean getSinistro(Long id) {
		EntityManager mgr = getEntityManager();
		SinistroBean sinistro = null;
		try {
			sinistro = mgr.find(SinistroBean.class, id);
		} finally {
			mgr.close();
		}
		return sinistro;
	}


	/**
	 * Insere um sinistro na base.
	 * 
	 * @param sinistro
	 * @return {@link SinistroBean}
	 */
	public SinistroBean insertSinistro(SinistroBean sinistro) {
		EntityManager mgr = getEntityManager();
		try {
			if (sinistro.getId() != null && containsSinistro(sinistro)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(sinistro);
		} finally {
			mgr.close();
		}
		return sinistro;
	}


	/**
	 * 
	 * Atualiza um sinistro na base
	 * 
	 * @param sinistro
	 * @return {@link SinistroBean}
	 */
	public SinistroBean updateSinistro(SinistroBean sinistro) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsSinistro(sinistro)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(sinistro);
		} finally {
			mgr.close();
		}
		return sinistro;
	}


	/**
	 * Remove um sinistro da base
	 * 
	 * @param id
	 */
	public void removeSinistro(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			SinistroBean sinistro = mgr.find(SinistroBean.class, id);
			mgr.remove(sinistro);
		} finally {
			mgr.close();
		}
	}

	/**
	 * Verifica se o sinistro ja existe.
	 * 
	 * @param sinistro
	 * @return
	 */
	private boolean containsSinistro(SinistroBean sinistro) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			SinistroBean item = mgr.find(SinistroBean.class, sinistro.getId());
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
