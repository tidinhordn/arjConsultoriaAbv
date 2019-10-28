package br.com.arjconsultoria.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Menuitem;

import br.com.arjconsultoria.bean.PerfilFuncao;
import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.bean.UsuarioCredencial;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.PerfilFuncaoService;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class ViewIndex extends MySelectorComposer<Component> {
	
	@WireVariable("perfilFuncaoService")
	private PerfilFuncaoService perfilFuncaoService;

	private Usuario usuario;
	private LoginController loginController;


	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		usuario = (Usuario) Executions.getCurrent().getArg().get("usuario");
		Session sess = Sessions.getCurrent();
		UsuarioCredencial cre = (UsuarioCredencial) sess.getAttribute("userCredential");
		loginController = (LoginController) Executions.getCurrent().getArg().get("controle");

		PerfilFuncao perfil = new PerfilFuncao();
		perfil.getId().setPerfilId(cre.getUsuario().getUsuarioId());
		List<String> permissoes = perfilFuncaoService.listarPermissoes(perfil);

		// List<PerfilFuncao> permissoesPerfil = perfilFuncaoService.listarPorPerfil(perfil);

		for (String permissao : permissoes) {

			try {
				if (!permissao.isEmpty()) {
					Menuitem menuItem = (Menuitem) comp.getFellow(permissao);
					menuItem.setVisible(true);
				}

			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}
	
	@Listen("onClick=#menuItemCadastrarEmpresa")
	public void exibirPesquisaEmpresa() {
		openWindow("cadastrar/pesquisarEmpresa.zul", "Pesquisar Empresa", null, OVERLAPPED);
	}

	@Listen("onClick=#menuItemCadastrarCliente")
	public void exibirPesquisaTaloes() {
		openWindow("cadastrar/pesquisarCliente.zul", "Pesquisar Cliente", null, OVERLAPPED);
	}
	@Listen("onClick=#menuItemPesquisarUsuario")
	public void exibirPesquisaUsuario() {
		openWindow("seguranca/pesquisarUsuario.zul", "Pesquisar Usuário", null, OVERLAPPED);
	}

	@Listen("onClick=#menuItemPesquisarPerfil")
	public void exibirPesquisaPerfil() {
		openWindow("seguranca/pesquisarPerfil.zul", "Pesquisar Perfil", null, OVERLAPPED);
	}
	
	@Listen("onClick=#menuItemSair")
	public void sair() {

		Session sess = Sessions.getCurrent();
		sess.removeAttribute("userCredential");
		Executions.sendRedirect("/");
	}
	
}
