package br.com.arjconsultoria.dao;

import br.com.arjconsultoria.bean.Usuario;

public interface UsuarioDAO {
	
	Usuario	buscaLogin(String login,String senha);
	
}
