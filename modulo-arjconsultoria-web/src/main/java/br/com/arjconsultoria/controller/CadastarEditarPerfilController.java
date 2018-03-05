package br.com.arjconsultoria.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.arjconsultoria.bean.FuncaoSistema;
import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.bean.PerfilFuncao;
import br.com.arjconsultoria.component.MyListbox;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.FuncaoSistemaService;
import br.com.arjconsultoria.service.PerfilFuncaoService;
import br.com.arjconsultoria.service.PerfilService;



public class CadastarEditarPerfilController extends
		MySelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@WireVariable("perfilService")
	private PerfilService perfilService;

	@Wire
	private Window winCadastrarEditarPerfil;
	@Wire
	private Button btnApagar;
	@Wire
	private Button btnSalvar;

	@Wire
	private Textbox txtPerfilId;
	@Wire
	private Textbox txtDescPerfil;

	@WireVariable("funcaoSistemaService")
	private FuncaoSistemaService funcaoSistemaService;

	@WireVariable("perfilFuncaoService")
	private PerfilFuncaoService perfilFuncaoService;

	private Comparator<FuncaoSistema> comparadorFuncao = (o1, o2) -> o1
			.compareTo(o2);

	private List<FuncaoSistema> funcaoRemovidas;

	@Wire
	private Combobox cmbFuncao;
	@Wire
	private MyListbox<FuncaoSistema> listFuncaoDisponiveis;
	@Wire
	private MyListbox<FuncaoSistema> listFuncaoSelecionadas;

	private Perfil perfil;

	private PerfilFuncao perfilFuncao;

	private Boolean novo;

	private PesquisarPerfilController pesquisarPerfilController;

	private final String MSG_ERRO_CAMPO_VAZIO = "O campo não pode ser vazio";

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		carregaFuncao();
		funcaoRemovidas = new ArrayList<>();

		perfil = (Perfil) Executions.getCurrent().getArg().get("perfil");
		pesquisarPerfilController = (PesquisarPerfilController) Executions
				.getCurrent().getArg().get("controle");

		novo = perfil == null;
		if (novo) {
			perfil = new Perfil();
			btnApagar.setDisabled(true);
		} else {

			List<PerfilFuncao> lsPerfilFuncao = perfilFuncaoService
					.listarPorPerfil(perfil);
			List<FuncaoSistema> lsFuncaoSistema = new ArrayList<FuncaoSistema>();
			for (PerfilFuncao perfilFuncao : lsPerfilFuncao) {

				lsFuncaoSistema.add(perfilFuncao.getFuncaoSistema());

			}
			listFuncaoSelecionadas.addAll(lsFuncaoSistema);
			listFuncaoSelecionadas.sort(comparadorFuncao);

		}

	}

	@Listen("onClick=#btnSalvar")
	public void salvar() {
		if (isCamposValidos()) {

			if (novo) {

				perfilService.inserir(perfil);
				for (FuncaoSistema funcaoSistema : listFuncaoSelecionadas
						.getAllItens()) {

					PerfilFuncao perfilFuncao = new PerfilFuncao();
					perfilFuncao.getId().setPerfilId(perfil.getPerfilId());
					perfilFuncao.getId().setfuncaoSistema(funcaoSistema.getFuncSistemaId());
					perfilFuncao.setPfAtivo(1);
					perfilFuncaoService.inserir(perfilFuncao);
				}

				Messagebox
						.show("Perfil salvo com sucesso",
								Labels.getLabel("CadastarEditarPerfilController.win.winCadastrarEditarPerfil"),
								Messagebox.OK, Messagebox.INFORMATION);
			} else {
				perfilService.alterar(perfil);
				//listFuncaoSelecionadas.getAllItens();
				perfilFuncaoService.apagarPorPerfilIdEFuncSistema(perfil.getPerfilId());

				for (FuncaoSistema funcaoSistema : listFuncaoSelecionadas.getAllItens()) {

					PerfilFuncao perfilFuncao = new PerfilFuncao();
					perfilFuncao.getId().setPerfilId(perfil.getPerfilId());
					perfilFuncao.getId().setfuncaoSistema(funcaoSistema.getFuncSistemaId());
					perfilFuncao.setPfAtivo(1);
					perfilFuncaoService.inserir(perfilFuncao);
				}

				Messagebox
						.show("Perfil alterado com sucesso",
								Labels.getLabel("CadastarEditarPerfilController.win.winCadastrarEditarPerfil"),
								Messagebox.OK, Messagebox.INFORMATION);
			}
			recarregaGridPesquisa();
			winCadastrarEditarPerfil.detach();
		}

	}

	@Listen("onClick=#btnSelecionarTodosFuncao")
	public void selecionarTodosFuncao() {
		listFuncaoSelecionadas.addAll(listFuncaoDisponiveis.getAllItens());
		listFuncaoDisponiveis.removeAll(listFuncaoSelecionadas.getAllItens());
		listFuncaoSelecionadas.sort(comparadorFuncao);

		// funcaoRemovidas.removeAll(listFuncaoDisponiveis.getAllItens());
	}

	@Listen("onClick=#btnRemoverFuncao")
	public void removerFuncao() {
		List<FuncaoSistema> selecionadas = listFuncaoSelecionadas
				.getSelectedsItens();
		// listFuncaoDisponiveis.addAll(selecionadas);
		listFuncaoSelecionadas.removeAll(selecionadas);
		listFuncaoDisponiveis.sort(comparadorFuncao);

		// funcaoRemovidas.addAll(selecionadas);

	}

	@Listen("onClick=#btnSelecionarFuncao")
	public void selecionarFuncao() {

		List<FuncaoSistema> selecionadas = listFuncaoDisponiveis
				.getSelectedsItens();
		listFuncaoSelecionadas.addAll(selecionadas);
		listFuncaoDisponiveis.removeAll(selecionadas);
		listFuncaoSelecionadas.sort(comparadorFuncao);

		// funcaoRemovidas.removeAll(selecionadas);
	}

	@Listen("onClick=#btnRemoverTodosFuncao")
	public void removerTodosFuncao() {
		listFuncaoDisponiveis.addAll(listFuncaoSelecionadas.getAllItens());
		listFuncaoSelecionadas.removeAll(listFuncaoDisponiveis.getAllItens());
		listFuncaoDisponiveis.sort(comparadorFuncao);

		// funcaoRemovidas.addAll(listFuncaoDisponiveis.getAllItens());
	}

	@Listen("onClick=#btnApagar")
	public void apagar() {

		Messagebox.show("Confirmar exclusão?", "Excluir", Messagebox.YES
				| Messagebox.NO, Messagebox.QUESTION,
				new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (((int) event.getData()) == Messagebox.YES) {
							perfilService.apagar(perfil);
							Messagebox.show("Exclusão concluída", "Concluído",
									Messagebox.OK, Messagebox.INFORMATION);
							recarregaGridPesquisa();
							winCadastrarEditarPerfil.detach();
						}
					}

				});

	}

	public void carregaFuncao() {

		List<FuncaoSistema> funcaoSis = new ArrayList<FuncaoSistema>();
		funcaoSis.addAll(getFuncao());
		listFuncaoDisponiveis.setData(new ListModelList<FuncaoSistema>(
				funcaoSis));
	}

	private List<FuncaoSistema> getFuncao() {
		List<FuncaoSistema> funcao = funcaoSistemaService.listarTodos();
		return funcao;
	}

	public List<FuncaoSistema> listarFuncao() {
		return funcaoSistemaService.listarTodos();
	}

	public Perfil getPerfil() {
		return perfil;
	}

	private boolean isCamposValidos() {

		Boolean valido = true;

		if (txtDescPerfil.getText().isEmpty()) {
			valido = false;
			Clients.wrongValue(txtDescPerfil, MSG_ERRO_CAMPO_VAZIO);
		}

		return valido;

	}

	private void recarregaGridPesquisa() {
		pesquisarPerfilController.recarregar();

	}

}
