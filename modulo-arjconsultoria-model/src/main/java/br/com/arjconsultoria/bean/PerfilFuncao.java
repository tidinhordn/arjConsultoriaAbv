package br.com.arjconsultoria.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity(name = "perfil_funcao")
public class PerfilFuncao {

	@Embeddable
	public static class Id implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "PERFIL_ID")
		private Integer perfilId;

		@Column(name = "FUNCSISTEMA")
		private Integer funcSistema;

		public Id() {
		}

		public Id(Integer perfilId, Integer funcSistema) {
			this.perfilId = perfilId;
			this.funcSistema = funcSistema;
		}

		public Integer getPerfilId() {
			return perfilId;
		}

		public void setPerfilId(Integer perfilId) {
			this.perfilId = perfilId;
		}

		public Integer getfuncaoSistema() {
			return funcSistema;
		}

		public void setfuncaoSistema(Integer funcSistema) {
			this.funcSistema = funcSistema;
		}
	}

	@EmbeddedId
	private Id id = new Id();
	@ManyToOne
	@JoinColumn(name = "FUNCSISTEMA", referencedColumnName = "FUNCSISTEMA_ID", insertable = false, updatable = false)
	private FuncaoSistema funcaoSistema;

	public PerfilFuncao() {
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public FuncaoSistema getFuncaoSistema() {
		return funcaoSistema;
	}

	public void setFuncaoSistema(FuncaoSistema funcaoSistema) {
		this.funcaoSistema = funcaoSistema;
	}

	@Column(name = "PFATIVO")
	private Integer pfAtivo;

	public Integer getPfAtivo() {
		return pfAtivo;
	}

	public void setPfAtivo(Integer pfAtivo) {
		this.pfAtivo = pfAtivo;
	}

}
