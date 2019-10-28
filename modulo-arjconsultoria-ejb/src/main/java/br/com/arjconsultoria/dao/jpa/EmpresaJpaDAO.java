package br.com.arjconsultoria.dao.jpa;

import br.com.arjconsultoria.bean.Empresa;
import br.com.arjconsultoria.dao.EmpresaDAO;

public class EmpresaJpaDAO  extends GenericJpaDAO<Empresa,Integer> implements EmpresaDAO {

	@Override
	public Class<Empresa> getPersistentClass() {
		// TODO Auto-generated method stub
		return Empresa.class;
	}

	@Override
	public void delete(Empresa entity) {
		// TODO Auto-generated method stub
		
	}

		

}
