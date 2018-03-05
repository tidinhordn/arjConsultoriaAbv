package br.com.arjconsultoria.dao;

import java.util.List;

import br.com.arjconsultoria.bean.Base;

public interface BaseDAO extends GenericDAO<Base, Integer> {
	
	List<Base> obterClienteBase(Base base);


}
