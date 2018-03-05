package br.com.arjconsultoria.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.bean.PerfilFuncao;
import br.com.arjconsultoria.dao.GenericDAO;
import br.com.arjconsultoria.dao.PerfilFuncaoDAO;



@Named
public class PerfilFuncaoService extends GenericServiceImpl<PerfilFuncao, Serializable>{

	@Inject
	private PerfilFuncaoDAO perfilFuncaoDAO;
	
	@Override
	protected GenericDAO<PerfilFuncao, Serializable> getDao() {
		return (GenericDAO<PerfilFuncao, Serializable>) perfilFuncaoDAO;
	}
	
	
	public List<String> listarPermissoes(PerfilFuncao perfil) {
		
		return perfilFuncaoDAO.listaPerm(perfil);
		
	}

	public List<PerfilFuncao> listarPorPerfil(Perfil perfil) {

		return perfilFuncaoDAO.listarPorPerfil(perfil);
	}


	public void apagarPorPerfilIdEFuncSistema(Integer perfilId) {
		 perfilFuncaoDAO.apagarPorPerfilIdEFuncSistema(perfilId);
		
	}


}

