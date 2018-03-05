package br.com.arjconsultoria.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.arjconsultoria.bean.Base;
import br.com.arjconsultoria.dao.BaseDAO;
import br.com.arjconsultoria.dao.GenericDAO;

@Named("baseService")
	@RequestScoped
public class BaseServiceImpl extends GenericServiceImpl<Base, Integer> implements BaseService {

	@Inject
	private BaseDAO baseDAO;

	@Override
	protected GenericDAO<Base, Serializable> getDao() {
		// TODO Auto-generated method stub
		return (GenericDAO) baseDAO;
	}

	@Override
	public List<Base> obterClienteBase(Base base) {
		// TODO Auto-generated method stub
		return baseDAO.obterClienteBase(base);
	}

}
