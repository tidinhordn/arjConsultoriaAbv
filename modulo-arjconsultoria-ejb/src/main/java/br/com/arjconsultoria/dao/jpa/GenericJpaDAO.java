package br.com.arjconsultoria.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import br.com.arjconsultoria.dao.GenericDAO;

public abstract class GenericJpaDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	@PersistenceContext(unitName="MySqlDS")
	private EntityManager entityManager;

	public GenericJpaDAO() {
	}
//
//	public GenericJpaDAO(Class<T> clazz) {
//		this.persistentClass = clazz;
//	}

	public abstract Class<T> getPersistentClass();

	@Override
	public T carregarId(ID id) {
		return entityManager.find(getPersistentClass(), id);
	}

	@Override
	@Transactional
	public T inserir(T entity) {
		entityManager.persist(entity);
		return entity;

	}

	@Override
	@Transactional
	public T alterar(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	public abstract void delete(T entity);

	@Override
	public List<T> carregarTodos() {
		System.out.println(getPersistentClass().getName());
		Query query = entityManager.createQuery("SELECT e FROM "
				+ getPersistentClass().getName() + " e");
		return query.getResultList();
	}

	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	@Override
	public Session getSessao() {
		return (Session) entityManager.getDelegate();
	}

}
