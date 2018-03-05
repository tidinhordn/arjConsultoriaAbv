package br.com.arjconsultoria.dao.jpa;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.dao.PerfilDAO;



@Named
@RequestScoped
public class PerfilJpaDAO extends GenericJpaDAO<Perfil, Serializable> implements PerfilDAO{


	@Override
	@Transactional
	public void delete(Perfil entity) {
		getEntityManager().remove(getEntityManager().getReference(entity.getClass(), entity.getPerfilId()));
	}

	@Override
	public Class<Perfil> getPersistentClass() {
		return Perfil.class;
	}

}
