package br.com.arjconsultoria.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.arjconsultoria.bean.FuncaoSistema;
import br.com.arjconsultoria.dao.FuncaoSistemaDAO;
import br.com.arjconsultoria.dao.jpa.GenericJpaDAO;;




@Named
@RequestScoped
public class FuncaoSistemaJpaDAO extends GenericJpaDAO<FuncaoSistema, Serializable> implements FuncaoSistemaDAO{


	@Override
	@Transactional
	public void delete(FuncaoSistema entity) {
		getEntityManager().remove(getEntityManager().getReference(entity.getClass(), entity.getFuncSistemaId()));
	}

	@Override
	public Class<FuncaoSistema> getPersistentClass() {
		return FuncaoSistema.class;
	}

	
}
