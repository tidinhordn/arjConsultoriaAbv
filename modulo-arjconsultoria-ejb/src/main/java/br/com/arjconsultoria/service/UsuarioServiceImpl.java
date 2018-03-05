package br.com.arjconsultoria.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.dao.GenericDAO;
import br.com.arjconsultoria.dao.UsuarioDAO;



@Named("usuarioService")
@RequestScoped
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioService {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected GenericDAO<Usuario, Serializable> getDao() {
		// TODO Auto-generated method stub
		return (GenericDAO) usuarioDAO;
	}
	
	@Override
	public Usuario buscaUsuario(String login , String senha){
		return  usuarioDAO.buscaLogin(login,senha);
	}
	
	
}
