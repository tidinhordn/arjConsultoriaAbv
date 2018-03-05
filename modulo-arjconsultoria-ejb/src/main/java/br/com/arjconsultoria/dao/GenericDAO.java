package br.com.arjconsultoria.dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;


/**
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared accross all DAO implementations.
 * The current design is for a state-management oriented persistence layer
 * (for example, there is no UDPATE statement function) that provides
 * automatic transactional dirty checking of business objects in persistent
 * state.
 *
 * @param <T> Tipo do Objeto da classe
 * @param <ID> Tipo do id do Objeto
 * @author Christian Bauer
 */
public interface GenericDAO<T, ID extends Serializable> {

	    T carregarId(ID id);

	    T inserir(T entity);

	    T alterar(T entity);

	    void delete(T entity);

	    List<T> carregarTodos();
	    
	    Session getSessao();

		
}
