package br.com.arjconsultoria.dao.jpa;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.arjconsultoria.bean.Usuario;
import br.com.arjconsultoria.dao.UsuarioDAO;



@Named
@RequestScoped
public class UsuarioJpaDAO extends GenericJpaDAO<Usuario, Serializable> implements UsuarioDAO{


	@Override
	@Transactional
	public void delete(Usuario entity) {
		getEntityManager().remove(getEntityManager().getReference(entity.getClass(), entity.getUsuarioId()));
	}

	@Override
	public Class<Usuario> getPersistentClass() {
		return Usuario.class;
	}
	
	@Override
	public Usuario buscaLogin(String login, String senha) {

		Query query = getEntityManager().createQuery("SELECT e FROM "+getPersistentClass().getName()+" e where e.login=:login and e.senha=:senha  ");
		query.setParameter("login",login);
		query.setParameter("senha",senha);
		
		try {
			return (Usuario) query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

}
