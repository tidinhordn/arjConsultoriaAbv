package br.com.arjconsultoria.dao.jpa;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.criterion.Example;

import br.com.arjconsultoria.bean.Base;
import br.com.arjconsultoria.dao.BaseDAO;

public class BasejpaDAO  extends GenericJpaDAO<Base,Integer> implements BaseDAO {


		@Override
		public void delete(Base entity) {
			// TODO Auto-generated method stub

		}

		@Override
		public Class<Base> getPersistentClass() {
			return Base.class;
		}
		
		@Override
		public List<Base> obterClienteBase(Base base){
			Example example = Example.create(base);
			List<Base> bases = getSessao().createCriteria(Base.class).add(example).list();
			return bases;			
		}

}
