package br.com.arjconsultoria.service;

import java.io.Serializable;
import java.util.List;


public interface GenericService <T,ID extends Serializable> {
	
	public abstract List<T> listarTodos();

	public abstract void alterar(T entity);

	public abstract T loadById(ID id);
	
	public abstract void inserir(T entity);

	public abstract void apagar(T entity);
	
}
