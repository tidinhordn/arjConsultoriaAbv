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

import br.com.arjconsultoria.component.MyListbox;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.PerfilService;
import br.com.arjconsultoria.bean.Perfil;



@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class PesquisarPerfilController extends
		MySelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@WireVariable("perfilService")
	private PerfilService perfilService;

	@Wire
	private Button btnNovo;
	@Wire
	private Button btnPesquisa;
	@Wire
	private Button btnRefresh;
	@Wire
	protected MyListbox<Perfil> perfilList;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		refreshLista();

		perfilList.addEventListener("onDoubleClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				Perfil perfil = (Perfil) perfilList
						.getSelected();
				abrirEdicao(perfil);
			}
		});
	}

	private void abrirEdicao(Perfil perfil) {
		if (perfil == null) {
			return;
		}

		Map<String,Object> args = new HashMap<>();
		Boolean novo = false;
		args.put("perfil", perfil);
		args.put("perfilList", perfilList);
		args.put("novo", novo);
		args.put("controle", this);

		this.openWindow("seguranca/cadastrarEditarPerfil.zul",
				"Perfil", args, MODAL);

	}

	@Listen("onClick=#btnNovo")
	public void criarNovo() {
		Map<String, Object> args = new HashMap<String, Object>();
		Boolean novo = true;
		args.put("novo", novo);
		args.put("controle", this);

		openWindow("seguranca/cadastrarEditarPerfil.zul", "Perfil", args, MODAL);
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
		List<Perfil> lista = perfilService.listarTodos();
		perfilList.setData(lista);
	}

}
