package br.com.arjconsultoria.controller;

import java.util.List;

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
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.component.MySelectorComposer;
import br.com.arjconsultoria.service.PerfilService;
import br.com.arjconsultoria.service.UsuarioService;





public class CadastarEditarUsuarioController extends MySelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@WireVariable("usuarioService")
	private UsuarioService usuarioService;
	
	@WireVariable("perfilService")
	private PerfilService perfilService;

	@Wire
	private Window winCadastrarEditarUsuario;
	@Wire
	private Button btnApagar;
	@Wire
	private Button btnSalvar;

	@Wire
	private Textbox txtUsuarioId;
	@Wire
	private Textbox txtLoginUsuario;
	@Wire
	private Textbox txtSenhaUsuario;
	@Wire
	private Textbox txtNomeUsuario;
	@Wire
	private Textbox txtEmailUsuario;
	@Wire
	private Combobox cmbPerfil;
	@Wire
	private Checkbox chkAtivo;
			
	private Usuario usuario;

	private Boolean novo;

	private PesquisarUsuarioController pesquisarUsuarioController;

	private final String MSG_ERRO_CAMPO_VAZIO = "O campo não pode ser vazio";

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		usuario = (Usuario) Executions.getCurrent().getArg().get("usuario");
		pesquisarUsuarioController = (PesquisarUsuarioController) Executions.getCurrent().getArg()
				.get("controle");

		novo = usuario == null;
		if (novo) {
			usuario = new Usuario();
			btnApagar.setDisabled(true);
		}else {
			cmbPerfil.setValue(usuario.getPerfil().getDescPerfil());
		}
	}

	@Listen("onClick=#btnSalvar")
	public void salvar() {
		if (isCamposValidos()) {

			if (novo) {
				usuario.setAtivo(1);
				usuarioService.inserir(usuario);
				Messagebox.show("Usuário salvo com sucesso",
						Labels.getLabel("CadastarEditarUsuarioController.win.winCadastrarEditarUsuario"), Messagebox.OK,
						Messagebox.INFORMATION);
			} else {
				usuarioService.alterar(usuario);
				Messagebox.show("Usuário alterado com sucesso",
						Labels.getLabel("CadastarEditarUsuarioController.win.winCadastrarEditarUsuario"), Messagebox.OK,
						Messagebox.INFORMATION);
			}
			recarregaGridPesquisa();
			winCadastrarEditarUsuario.detach();
		}

	}

	@Listen("onClick=#btnApagar")
	public void apagar() {

		Messagebox.show("Confirmar exclusão?", "Excluir", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
				new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (((int) event.getData()) == Messagebox.YES) {
							usuarioService.apagar(usuario);
							Messagebox.show("Exclusão concluída", "Concluído", Messagebox.OK, Messagebox.INFORMATION);
							recarregaGridPesquisa();
							winCadastrarEditarUsuario.detach();
						}
					}

				});

	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Perfil> listarPerfil() {
		return perfilService.listarTodos();
	}

	private boolean isCamposValidos() {

		Boolean valido = true;

		if (txtLoginUsuario.getText().isEmpty()) {
			valido = false;
			Clients.wrongValue(txtLoginUsuario, MSG_ERRO_CAMPO_VAZIO);
		}

		if (txtSenhaUsuario.getText().isEmpty()) {
			valido = false;
			Clients.wrongValue(txtSenhaUsuario, MSG_ERRO_CAMPO_VAZIO);
		}

		if (txtNomeUsuario.getText().isEmpty()) {
			valido = false;
			Clients.wrongValue(txtNomeUsuario, MSG_ERRO_CAMPO_VAZIO);
		}

		if (txtEmailUsuario.getText().isEmpty()) {
			valido = false;
			Clients.wrongValue(txtEmailUsuario, MSG_ERRO_CAMPO_VAZIO);
		}

		return valido;

	}

	private void recarregaGridPesquisa() {
		pesquisarUsuarioController.recarregar();

	}

}
