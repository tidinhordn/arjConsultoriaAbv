package br.com.arjconsultoria.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity(name = "funcao_sistema")
public class FuncaoSistema implements Serializable, Comparable<FuncaoSistema> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FUNCSISTEMA_ID")
	private Integer funcSistemaId;

	@Column(name = "SISTEMA_ID")
	private Integer sistemaId;

	@Column(name = "NOME_FUNCAO")
	private String nomeFuncao;

	@Column(name = "FUNCAO")
	private String funcao;

	@Column(name = "ATIVO")
	private Integer ativo;

	public Integer getFuncSistemaId() {
		return funcSistemaId;
	}

	public void setFuncSistemaId(Integer funcSistemaId) {
		this.funcSistemaId = funcSistemaId;
	}

	public Integer getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Integer sistemaId) {
		this.sistemaId = sistemaId;
	}

	public String getNomeFuncao() {
		return nomeFuncao;
	}

	public void setNomeFuncao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	@Override
	public int compareTo(FuncaoSistema o) {
		return this.getFuncSistemaId().compareTo(o.getFuncSistemaId());
	}

}
