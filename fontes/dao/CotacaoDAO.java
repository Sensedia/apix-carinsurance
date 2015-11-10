package com.sensedia.apix.server.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.sensedia.apix.server.model.CotacaoBean;

/**
 * 
 * Classe DAO para gerenciamento dos recursos relacionados 
 * a cotação.
 * 
 * @author Sensedia
 *
 */
public class CotacaoDAO {

	/**
	 * Recupera a lista de cotações
	 * 
	 * @param cursorString
	 * @param offset
	 * @param limit
	 * @param valorMinimo
	 * @param valorMaximo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<CotacaoBean> listCotacao(String cursorString, Integer offset,
			Integer limit, Double valorMinimo, Double valorMaximo) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<CotacaoBean> execute = null;

		try {
			mgr = getEntityManager();
			
			CriteriaBuilder cb = mgr.getCriteriaBuilder();
			CriteriaQuery<CotacaoBean> criteria = cb.createQuery(CotacaoBean.class);
			Root<CotacaoBean> root = criteria.from(CotacaoBean.class);

			Expression<Double> path = root.get("valorApolice");
			
			if (valorMinimo != null) {
				Predicate vlMinPredicate = cb.greaterThanOrEqualTo(path, valorMinimo);
				criteria.where(vlMinPredicate);
			}

			if (valorMaximo != null) {
				Predicate vlMaxPredicate = cb.lessThanOrEqualTo(path, valorMaximo);
				criteria.where(vlMaxPredicate);
			}		
			
			Query query = mgr.createQuery(criteria);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (offset != null) {
				query.setFirstResult(offset);				
			}
			
			if (limit != null) {
				query.setMaxResults(limit);
			}

			execute = (List<CotacaoBean>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Itera sobre os recursos encontrados para
			// carregamento em lazy fetch
			for (CotacaoBean obj : execute)
				;

		} finally {
			mgr.close();
		}

		return execute;
	}

	/**
	 * Recupera acotação pelo id
	 * 
	 * @param id
	 * @return
	 */
	public CotacaoBean getCotacao(Long id) {
		EntityManager mgr = getEntityManager();
		CotacaoBean cotacao = null;
		try {
			cotacao = mgr.find(CotacaoBean.class, id);
		} finally {
			mgr.close();
		}
		return cotacao;
	}

	/**
	 * Insere uma cotação
	 * 
	 * @param cotacao
	 * @return {@link CotacaoBean}
	 */
	public CotacaoBean insertCotacao(CotacaoBean cotacao) {
		EntityManager mgr = getEntityManager();
		try {
			if (cotacao.getId() != null && containsCotacao(cotacao)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(cotacao);
		} finally {
			mgr.close();
		}
		return cotacao;
	}

	/**
	 * Atualiza uma cotação
	 * 
	 * @param cotacao
	 * @return {@link CotacaoBean}
	 */
	public CotacaoBean updateCotacao(CotacaoBean cotacao) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCotacao(cotacao)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(cotacao);
		} finally {
			mgr.close();
		}
		return cotacao;
	}

	/**
	 * Remove uma cotação
	 * 
	 * @param id
	 */
	public void removeCotacao(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			CotacaoBean cotacao = mgr.find(CotacaoBean.class, id);
			mgr.remove(cotacao);
		} finally {
			mgr.close();
		}
	}

	/**
	 * Verifica se a coção já existe.
	 * 
	 * @param cotacao
	 * @return
	 */
	private boolean containsCotacao(CotacaoBean cotacao) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			CotacaoBean item = mgr.find(CotacaoBean.class, cotacao.getId());
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
	 * 
	 * @return {@link EntityManager}
	 */
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
