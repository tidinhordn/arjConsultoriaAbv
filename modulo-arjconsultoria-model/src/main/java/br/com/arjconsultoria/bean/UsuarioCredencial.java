package br.com.arjconsultoria.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsuarioCredencial implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;
	private String name;
	private Usuario usuario;

	Set<String> roles = new HashSet<String>();

	public UsuarioCredencial(Usuario usuario) {
		this.account = account;
		this.name = name;
		this.usuario = usuario;
	}

	public UsuarioCredencial() {
		this.account = "anonymous";
		this.name = "Anonymous";
		roles.add("anonymous");
	}

	public boolean isAnonymous() {
		return hasRole("anonymous") || "anonymous".equals(account);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasRole(String role) {
		return roles.contains(role);
	}

	public void addRole(String role) {
		roles.add(role);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
