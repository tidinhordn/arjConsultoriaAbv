package br.com.arjconsultoria.controller;

import java.awt.Button;
import java.awt.Window;
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

import br.com.arjconsultoria.bean.Base;
import br.com.arjconsultoria.component.BaseRender;
import br.com.arjconsultoria.component.MyListbox;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.BaseService;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class PesquisarClienteController extends MySelectorComposer<Component> {

	@WireVariable("baseService")
	private BaseService baseService;
	@Wire
	private Window pesquisarCliente;
	@Wire
	private Button btnNovo;

	@Wire
	private Button btnPesquisa;
	@Wire
	private Button btnRefresh;
	@Wire
	protected MyListbox<Base> clienteList;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		refreshLista();
		clienteList.addEventListener("onDoubleClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				Base caixa = (Base) clienteList.getSelected();
				abrirEdicao(caixa);
			}
		});
	}

	private void abrirEdicao(Base t) {
		if (t == null) {
			return;
		}
		Map<String, Object> args = new HashMap<>();
		Boolean novo = false;
		args.put("base", t);
		args.put("clienteList", clienteList);
		args.put("novo", novo);
		args.put("controle", this);
		this.openWindow("cadastrar/cadastrarEditarCliente.zul", "Base", args, MODAL);
	}

	@Listen("onClick=#btnNovo")
	public void criarNovo(Event e) {
		Map<String, Object> args = new HashMap<String, Object>();
		Boolean novo = true;
		args.put("novo", novo);
		args.put("controle", this);
		openWindow("cadastrar/cadastrarEditarCliente.zul", "Base", args, MODAL);
	}

	private void refreshLista() {
		/*List<Base> lista = baseService.listarTodos();*/
		Base base = new Base();
		base.setCidade("Belo horizonte");
		List<Base> lista = baseService.obterClienteBase(base);
		clienteList.setData(lista);
		clienteList.setItemRenderer(new BaseRender());
	}

	@Listen("onClick=#btnRefresh")
	public void recarregar() {
		refreshLista();
	}

	public Button getBtnNovo() {
		return btnNovo;
	}

	public void setBtnNovo(Button btnNovo) {
		this.btnNovo = btnNovo;
	}

	public Button getBtnPesquisa() {
		return btnPesquisa;
	}

	public void setBtnPesquisa(Button btnPesquisa) {
		this.btnPesquisa = btnPesquisa;
	}

	public Button getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(Button btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public MyListbox<Base> getClienteList() {
		return clienteList;
	}

	public void setClienteList(MyListbox<Base> clienteList) {
		this.clienteList = clienteList;
	}
}
