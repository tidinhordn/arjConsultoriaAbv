package br.com.arjconsultoria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;

import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.component.MyListbox;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.UsuarioService;


@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class PesquisarUsuarioController extends
		MySelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@WireVariable("usuarioService")
	private UsuarioService usuarioService;
	@Wire
	private Button btnNovo;
	@Wire
	private Button btnPesquisa;
	@Wire
	private Button btnRefresh;
	@Wire
	protected MyListbox<Usuario> usuarioList;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		refreshLista();

		usuarioList.addEventListener("onDoubleClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				Usuario usuario = (Usuario) usuarioList
						.getSelected();
				abrirEdicao(usuario);
			}
		});
	}

	private void abrirEdicao(Usuario usuario) {
		if (usuario == null) {
			return;
		}

		Map<String,Object> args = new HashMap<>();
		Boolean novo = false;
		args.put("usuario", usuario);
		args.put("usuarioList", usuarioList);
		args.put("novo", novo);
		args.put("controle", this);

		this.openWindow("seguranca/cadastrarEditarUsuario.zul",
				"Usuario", args, MODAL);

	}

	@Listen("onClick=#btnNovo")
	public void criarNovo() {
		Map<String, Object> args = new HashMap<String, Object>();
		Boolean novo = true;
		args.put("novo", novo);
		args.put("controle", this);

		openWindow("seguranca/cadastrarEditarUsuario.zul", "Usuario", args, MODAL);
	}

	@Listen("onClick=#btnRefresh")
	public void recarregar() {
		refreshLista();
	}

	@Listen("onClick=#btnPesquisa")
	public void pesquisar() {
		refreshLista();
	}


	private void refreshLista() {
		List<Usuario> lista = usuarioService.listarTodos();
		usuarioList.setData(lista);
	}

}
