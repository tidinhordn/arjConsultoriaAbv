package br.com.arjconsultoria.service;

import java.io.Serializable;
import java.util.List;

import br.com.arjconsultoria.dao.GenericDAO;

public abstract class GenericServiceImpl<T,ID extends Serializable> implements GenericService<T, ID>{


	@Override
	public List<T> listarTodos() {
		return getDao().carregarTodos();
	}

	@Override
	public void alterar(T entity) {
		getDao().alterar(entity);

	}

	@Override
	public T loadById(ID id) {
		return getDao().carregarId(id);
	}

	@Override
	public void inserir(T entity) {
		getDao().inserir(entity);
	}
	
	@Override
	public void apagar(T entity) {
		getDao().delete(entity);
	}

	@SuppressWarnings({ })
	protected abstract GenericDAO<T, Serializable> getDao();

	
	

}
