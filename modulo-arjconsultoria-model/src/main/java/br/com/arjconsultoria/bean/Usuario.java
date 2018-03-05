package br.com.arjconsultoria.bean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name ="usuario")
public class Usuario implements Serializable, Comparable<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USUARIO_ID")
	private Integer usuarioId;
	
	@Column(name = "LOGIN_USUARIO")
	private String login;

	@Column(name = "SENHA_USUARIO")
	private String senha;

	@Column(name = "NOME_USUARIO")
	private String nomeUsuario;

	@Column(name = "EMAIL_USUARIO")
	private String emailUsuario;
	
	@Column(name = "ATIVO")
	private Integer ativo;
	
	@ManyToOne
	@JoinColumn(name="PERFIL_ID")
	private Perfil perfil;
	
			
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int compareTo(Usuario arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
		
}
