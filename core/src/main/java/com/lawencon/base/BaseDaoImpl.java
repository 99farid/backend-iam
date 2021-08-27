package com.lawencon.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import com.lawencon.helper.Callback;

/**
 * <p>
 * Abstract DAO untuk handle basic CRUD
 * <p>
 * Setiap class child wajib set constructor untuk Entity yang digunakan
 * <p>
 * Sudah include method pre/post create, update, dan delete
 * <p>
 * Gunakan @override untuk mendefinisikan method pre/post sesuai kebutuhan
 * 
 * @author Agung Damas Saputra
 * 
 */
@Repository
public class BaseDaoImpl<T extends BaseEntity> {

	public Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
	}

	protected T getById(final String id) {
		return em().find(clazz, id);
	}

	protected List<T> getAll() {
		return em().createQuery("FROM " + clazz.getName(), clazz).getResultList();
	}

	protected void save(final T entity, Callback before, Callback after) throws Exception {
		if (before != null)
			before.exec();

		if (entity.getId() != null) {
			em().merge(entity);
		} else {
			em().persist(entity);
		}

		if (after != null)
			after.exec();
	}

	protected void delete(final T entity, Callback before, Callback after) throws Exception {
		if (before != null)
			before.exec();

		em().remove(entity);

		if (after != null)
			after.exec();
	}

	protected void deleteById(final Object entityId) throws Exception {
		T entity = null;
		if (entityId != null && entityId instanceof String) {
			entity = getById((String) entityId);
		}

		if (entity != null)
			delete(entity, null, null);
		else
			throw new Exception("ID Not Found");
	}

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	protected <C> TypedQuery<C> createQuery(String sql, Class<C> clazz) {
		return em().createQuery(sql, clazz);
	}

	protected Query createNativeQuery(String sql) {
		return em().createNativeQuery(sql);
	}

}