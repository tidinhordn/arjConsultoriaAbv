package br.com.arjconsultoria.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.arjconsultoria.bean.Empresa;
import br.com.arjconsultoria.dao.EmpresaDAO;
import br.com.arjconsultoria.dao.GenericDAO;

@Named("empresaService")
@RequestScoped
public class EmpresaServiceImpl extends GenericServiceImpl<Empresa, Integer> implements EmpresaService {
	@Inject
	private EmpresaDAO empresaDAO;
	@Override
	protected GenericDAO<Empresa, Serializable> getDao() {
		// TODO Auto-generated method stub
		return (GenericDAO) empresaDAO;
	}

}
