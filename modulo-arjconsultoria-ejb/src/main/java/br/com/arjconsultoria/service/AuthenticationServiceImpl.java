package br.com.arjconsultoria.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.bean.UsuarioCredencial;
import br.com.arjconsultoria.dao.UsuarioDAO;




@Named("authService")
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WireVariable("usuarioService")
	private UsuarioService usuarioService;
	@Inject
	private UsuarioDAO usuarioDAO;
	private Usuario usuario;

	@Override
	public boolean login(String account, String password) {

		usuario = usuarioDAO.buscaLogin(account, password);

		if (usuario == null) {
			return false;
		}
		Session sess = Sessions.getCurrent();
		HttpSession session = (HttpSession) sess.getNativeSession();
		UsuarioCredencial cre = new UsuarioCredencial(usuario);
		sess.setAttribute("userCredential", cre);

		return true;

	}

	@Override
	public void logout() {
		Session sess = Executions.getCurrent().getSession();
		sess.removeAttribute("userCredential");
	}

	@Override
	public UsuarioCredencial getUserCredential() {

		Session sess = Sessions.getCurrent();
		UsuarioCredencial cre = (UsuarioCredencial) sess.getAttribute("userCredential");
		if (cre == null) {
			cre = new UsuarioCredencial();// new a anonymous user and set to
											// session
			sess.setAttribute("userCredential", cre);
		}
		return cre;

	}

	public synchronized boolean isAuthenticated() {
		return usuario != null;
	}

}
