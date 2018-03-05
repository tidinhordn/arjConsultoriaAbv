package br.com.arjconsultoria.controller;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.cdi.DelegatingVariableResolver;

import br.com.arjconsultoria.bean.UsuarioCredencial;
import br.com.arjconsultoria.service.AuthenticationService;



public class AuthenticationInitController implements Initiator {

	private AuthenticationService authService;

	public void doInit(Page page, Map<String, Object> args) throws Exception {

		if (authService == null) {
			authService = (AuthenticationService) new DelegatingVariableResolver().resolveVariable("authService");

			UsuarioCredencial cre = getUserCredential();
			if (cre == null || cre.isAnonymous()) {
				Executions.sendRedirect("/login.zul");
				return;
			}
		}
	}

	public UsuarioCredencial getUserCredential() {

		Session sess = Sessions.getCurrent();
		UsuarioCredencial cre = (UsuarioCredencial) sess.getAttribute("userCredential");
		if (cre == null) {
			cre = new UsuarioCredencial();
			sess.setAttribute("userCredential", cre);
		}
		return cre;

	}

}
