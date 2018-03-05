package br.com.arjconsultoria.dao;

import java.io.Serializable;
import java.util.List;

import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.bean.PerfilFuncao;



public interface PerfilFuncaoDAO extends GenericDAO<PerfilFuncao, Serializable>{
	
	List<String> listaPerm(PerfilFuncao perfil);

	List<PerfilFuncao> listarPorPerfil(Perfil perfil);

	public void apagarPorPerfilIdEFuncSistema(Integer perfilId);


}
