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

import br.com.arjconsultoria.bean.Empresa;
import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.component.MyListbox;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.EmpresaService;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class PesquisarEmpresaController  extends
MySelectorComposer<Component> {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WireVariable("empresaService")
	private EmpresaService empresaService;
	@Wire
	private Button btnNovo;
	@Wire
	private Button btnPesquisa;
	@Wire
	private Button btnRefresh;
	@Wire
	protected MyListbox<Empresa> empresaList;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		refreshLista();
		

		empresaList.addEventListener("onDoubleClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				Empresa empresa = (Empresa) empresaList
						.getSelected();
				abrirEdicao(empresa);
			}
		});
	
	}
	
	@Listen("onClick=#btnNovo")
	public void criarNovo() {
		Map<String, Object> args = new HashMap<String, Object>();
		Boolean novo = true;
		args.put("novo", novo);
		args.put("controle", this);

		openWindow("cadastrar/cadastrarEditarEmpresa.zul", "Epresa", args, MODAL);
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
		List<Empresa> lista = empresaService.listarTodos();
		empresaList.setData(lista);
	}
	
	private void abrirEdicao(Empresa empresa) {
		if (empresa == null) {
			return;
		}

		Map<String,Object> args = new HashMap<>();
		Boolean novo = false;
		args.put("empresa", empresa);
		args.put("empresaList", empresaList);
		args.put("novo", novo);
		args.put("controle", this);

		this.openWindow("cadastrar/cadastrarEditarEmpresa.zul",
				"Empresa", args, MODAL);

	}
	
	

}
