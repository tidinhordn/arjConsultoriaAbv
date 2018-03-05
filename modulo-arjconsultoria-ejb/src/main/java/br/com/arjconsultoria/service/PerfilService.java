package br.com.arjconsultoria.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.dao.GenericDAO;
import br.com.arjconsultoria.dao.PerfilDAO;



@Named
public class PerfilService extends GenericServiceImpl<Perfil, Serializable>{

	@Inject
	private PerfilDAO perfilDAO;
	
	@Override
	protected GenericDAO<Perfil, Serializable> getDao() {
		return (GenericDAO<Perfil, Serializable>) perfilDAO;
	}

}
