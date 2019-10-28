package br.com.arjconsultoria.controller;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import br.com.arjconsultoria.bean.Empresa;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.EmpresaService;

public class CadastarEditarEmpresaController  extends MySelectorComposer<Component> {
	
	@WireVariable("empresaService")
	private EmpresaService empresaService;
	
	@Wire
	private Window winCadastrarEditarEmpresa;
	@Wire
	private Button btnApagar;
	@Wire
	private Button btnSalvar;
	
	private final String MSG_ERRO_CAMPO_VAZIO = "O campo não pode ser vazio";
	
	private Empresa empresa;

	private Boolean novo;

	private PesquisarEmpresaController pesquisarEmpresaController;
	
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		empresa = (Empresa) Executions.getCurrent().getArg().get("empresa");
		pesquisarEmpresaController = (PesquisarEmpresaController) Executions.getCurrent().getArg()
				.get("controle");

		novo = empresa == null;
		if (novo) {
			empresa = new Empresa();
			btnApagar.setDisabled(true);
		}
	}
	
	
	@Listen("onClick=#btnSalvar")
	public void salvar() {
		if (isCamposValidos()) {

			if (novo) {
				
				empresaService.inserir(empresa);
				Messagebox.show("Empresa salvo com sucesso",
						Labels.getLabel("CadastarEditarEmpresaController.win.winCadastrarEditarEmpresa"), Messagebox.OK,
						Messagebox.INFORMATION);
			} else {
				empresaService.alterar(empresa);
				Messagebox.show("Usuário alterado com sucesso",
						Labels.getLabel("CadastarEditarEmpresaController.win.winCadastrarEditarEmpresa"), Messagebox.OK,
						Messagebox.INFORMATION);
			}
			recarregaGridPesquisa();
			winCadastrarEditarEmpresa.detach();
		}

	}

	@Listen("onClick=#btnApagar")
	public void apagar() {

		Messagebox.show("Confirmar exclusão?", "Excluir", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
				new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (((int) event.getData()) == Messagebox.YES) {
							empresaService.apagar(empresa);
							Messagebox.show("Exclusão concluída", "Concluído", Messagebox.OK, Messagebox.INFORMATION);
							recarregaGridPesquisa();
							winCadastrarEditarEmpresa.detach();
						}
					}

				});

	}

	
	

	private boolean isCamposValidos() {

		Boolean valido = true;
//
//		if (txtLoginUsuario.getText().isEmpty()) {
//			valido = false;
//			Clients.wrongValue(txtLoginUsuario, MSG_ERRO_CAMPO_VAZIO);
//		}
//
//		if (txtSenhaUsuario.getText().isEmpty()) {
//			valido = false;
//			Clients.wrongValue(txtSenhaUsuario, MSG_ERRO_CAMPO_VAZIO);
//		}
//
//		if (txtNomeUsuario.getText().isEmpty()) {
//			valido = false;
//			Clients.wrongValue(txtNomeUsuario, MSG_ERRO_CAMPO_VAZIO);
//		}
//
//		if (txtEmailUsuario.getText().isEmpty()) {
//			valido = false;
//			Clients.wrongValue(txtEmailUsuario, MSG_ERRO_CAMPO_VAZIO);
//		}

		return valido;

	}

	private void recarregaGridPesquisa() {
		pesquisarEmpresaController.recarregar();

	}


}
