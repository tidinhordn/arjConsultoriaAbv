package br.com.arjconsultoria.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.arjconsultoria.bean.FuncaoSistema;
import br.com.arjconsultoria.dao.FuncaoSistemaDAO;
import br.com.arjconsultoria.dao.GenericDAO;



@Named
public class FuncaoSistemaService extends GenericServiceImpl<FuncaoSistema, Serializable>{

	@Inject
	private FuncaoSistemaDAO funcaoSistemaDAO;
	
	@Override
	protected GenericDAO<FuncaoSistema, Serializable> getDao() {
		return (GenericDAO<FuncaoSistema, Serializable>) funcaoSistemaDAO;
	}
	
	/*public List<FuncaoSistema> listarTodo(){
		List<FuncaoSistema> lista = funcaoSistemaDAO.listarTodo();
		lista.sort((l1,l2)->l1.getFuncSistemaId().compareTo(l2.getFuncSistemaId()));
		return lista;
	}*/

}
