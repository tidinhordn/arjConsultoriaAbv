package br.com.arjconsultoria.dao.jpa;


import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.arjconsultoria.bean.Perfil;
import br.com.arjconsultoria.bean.PerfilFuncao;
import br.com.arjconsultoria.dao.PerfilFuncaoDAO;


@Named
@RequestScoped
public class PerfilFuncaoJpaDAO extends	GenericJpaDAO<PerfilFuncao, Serializable> implements PerfilFuncaoDAO {

	@Override
	@Transactional
	public void delete(PerfilFuncao entity) {
		getEntityManager().remove(
				getEntityManager().getReference(entity.getClass(),
						entity.getId()));
	}

	@Override
	public Class<PerfilFuncao> getPersistentClass() {
		return PerfilFuncao.class;
	}
	@Override
	public List<String> listaPerm(PerfilFuncao perfil) {

		StringBuilder sqln = new StringBuilder(
				"select f.funcao from perfil_funcao p inner join funcao_sistema f on (p.funcsistema = f.funcsistema_id) "
				+ "where p.perfil_id ="+ perfil.getId().getPerfilId()+" and p.pfativo = 1");
		
		Query query = getEntityManager().createNativeQuery(sqln.toString());
		
		return query.getResultList();
	}

	@Override
	public List<PerfilFuncao> listarPorPerfil(Perfil perfil) {
		
		Session session = (Session) getEntityManager().getDelegate();
		Criteria c = session.createCriteria(getPersistentClass());
		c.add(Restrictions.eq("id.perfilId", perfil.getPerfilId()));

//		StringBuilder sql = new StringBuilder();
//		sql.append("select e from "+getPersistentClass().getName()+" e join e.Id.funcSistema c where 1 = 1 ")
//		.append("and e.perfilId = ").append(perfil.getPerfilId());
//
//		TypedQuery<PerfilFuncao> query = getEntityManager().createQuery(sql.toString(), PerfilFuncao.class);
//
//		List<PerfilFuncao> historicos = query.getResultList();
		
		List<PerfilFuncao> historicos  = c.list();

		return historicos;
	}

	@Transactional
	@Override
	public void apagarPorPerfilIdEFuncSistema(Integer perfilId) {
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append("select e from "+getPersistentClass().getName()+" e where 1 = 1 ")
//		.append("and e.perfilId = ").append(perfilId);

		
		Session session = (Session) getEntityManager().getDelegate();
		
		SQLQuery query = session.createSQLQuery("delete from perfil_funcao where perfil_id = "+perfilId+"");
		
		query.executeUpdate();
		
//		Criteria c = session.createCriteria(getPersistentClass());
//		c.add(Restrictions.eq("id.perfilId", perfilId));
//		
//	//	TypedQuery<PerfilFuncao> query = getEntityManager().createQuery(sql.toString(), PerfilFuncao.class);
//		
//		PerfilFuncao perfilFuncao  = (PerfilFuncao) c.uniqueResult();
//		
//		getEntityManager().remove(getEntityManager().getReference(PerfilFuncao.class, perfilFuncao.getId()));
	
	}



}
