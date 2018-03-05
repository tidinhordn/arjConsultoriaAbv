package br.com.arjconsultoria.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.bean.UsuarioCredencial;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.UsuarioService;



@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class LoginController extends MySelectorComposer<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox txtUsuario;
	@Wire
	private Textbox txtSenha;
	@Wire
	private Button btnLogar;
/*	@WireVariable("authService")
	private AuthenticationService authService;*/
	@WireVariable("usuarioService")
	private UsuarioService usuarioService;
	@Wire
	private Window winLogin;

	@Override
	public void doAfterCompose(org.zkoss.zk.ui.Component comp) throws Exception {

		super.doAfterCompose(comp);

		Session sess = Sessions.getCurrent();
		UsuarioCredencial cre = (UsuarioCredencial) sess.getAttribute("userCredential");
		if (cre == null || cre.isAnonymous()) {
			return;
		}
	}

	public Textbox getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(Textbox txtSenha) {
		this.txtSenha = txtSenha;
	}

	public Textbox getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(Textbox txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	@Listen("onClick=#btnLogar; onOK=#winLogin")
	public void buscaUsuario() {

		Map<String, Object> args = new HashMap<>();
		Boolean novo = false;
		args.put("usuario", getTxtUsuario());
		args.put("novo", novo);
		args.put("controle", this);

		String usuarioTxt = txtUsuario.getValue() != null ? txtUsuario.getValue().toString() : null;
		String senhaTxt = txtSenha.getValue() != null ? txtSenha.getValue().toString() : null;

		Usuario usuario = usuarioService.buscaUsuario(usuarioTxt, senhaTxt);

		if (usuario != null) {
			Session sess = Sessions.getCurrent();
			UsuarioCredencial cre = new UsuarioCredencial(usuario);
			sess.setAttribute("userCredential", cre);
			args.put("usuario", cre.getUsuario());
			winLogin.detach();
			final Window win = (Window) Executions.createComponents("/index.zul", null, args);
		} else {
			Messagebox.show("Usuário ou Senha inválidos! Tente novamente.");
		}
		Executions.sendRedirect("/");

	}

}
