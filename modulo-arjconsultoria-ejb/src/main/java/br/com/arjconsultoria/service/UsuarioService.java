package br.com.arjconsultoria.service;

import br.com.arjconsultoria.bean.Usuario;

public interface UsuarioService extends GenericService<Usuario, Integer>{

	Usuario buscaUsuario(String login, String senha);
		
}
