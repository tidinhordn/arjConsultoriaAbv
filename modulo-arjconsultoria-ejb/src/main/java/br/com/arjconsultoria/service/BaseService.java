package br.com.arjconsultoria.service;

import java.util.List;

import br.com.arjconsultoria.bean.Base;

public interface BaseService extends GenericService<Base, Integer>{
	
	public List<Base> obterClienteBase(Base base);

}
