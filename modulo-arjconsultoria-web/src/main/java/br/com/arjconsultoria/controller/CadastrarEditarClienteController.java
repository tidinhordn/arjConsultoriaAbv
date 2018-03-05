package br.com.arjconsultoria.controller;


import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import br.com.arjconsultoria.bean.Base;
import br.com.arjconsultoria.component.MySelectorComposer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class CadastrarEditarClienteController extends MySelectorComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Window winCadastrarEditarCliente;
	
	@Wire
	private Button btnApagar;
	@Wire
	private Button btnSalvar;

	private Base base;
	private Boolean novo;
	private PesquisarClienteController pesquisarClienteController;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
        System.out.print("passei aqui");
		base = (Base) Executions.getCurrent().getArg().get("base");
		novo = (Boolean) Executions.getCurrent().getArg().get("novo");
		pesquisarClienteController = (PesquisarClienteController) Executions.getCurrent().getArg().get("controle");

		if (base == null) {
			base = new Base();
			btnApagar.setDisabled(true);
		}
	}
	
	public Window getWinCadastrarEditarCliente() {
		return winCadastrarEditarCliente;
	}

	public void setWinCadastrarEditarCliente(Window winCadastrarEditarCliente) {
		this.winCadastrarEditarCliente = winCadastrarEditarCliente;
	}

	public Button getBtnApagar() {
		return btnApagar;
	}

	public void setBtnApagar(Button btnApagar) {
		this.btnApagar = btnApagar;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public PesquisarClienteController getPesquisarClienteController() {
		return pesquisarClienteController;
	}

	public void setPesquisarClienteController(PesquisarClienteController pesquisarClienteController) {
		this.pesquisarClienteController = pesquisarClienteController;
	}


}
