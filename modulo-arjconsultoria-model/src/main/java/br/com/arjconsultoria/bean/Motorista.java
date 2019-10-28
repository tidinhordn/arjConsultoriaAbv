package br.com.arjconsultoria.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="motorista")
public class Motorista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer motoristaId;	
	private String nome;
	private String cpf;
	private String rg;
	private String numeroCnh;
	public Integer getMotoristaId() {
		return motoristaId;
	}
	public void setMotoristaId(Integer motoristaId) {
		this.motoristaId = motoristaId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNumeroCnh() {
		return numeroCnh;
	}
	public void setNumeroCnh(String numeroCnh) {
		this.numeroCnh = numeroCnh;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
