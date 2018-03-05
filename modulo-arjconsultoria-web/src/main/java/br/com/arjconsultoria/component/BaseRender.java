package br.com.arjconsultoria.component;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import br.com.arjconsultoria.bean.Base;

public class BaseRender implements ListitemRenderer<Base> {

	@Override
	public void render(Listitem item, Base base, int index) throws Exception {
		// TODO Auto-generated method stub
		Listcell lc = new Listcell(base.getNome());
		lc.setParent(item);
		lc = new Listcell(base.getCidade());
		lc.setParent(item);
		lc = new Listcell(base.getTel1());
		lc.setParent(item);
		lc = new Listcell(base.getCelular());
		lc.setParent(item);
		lc = new Listcell(base.getMarca());
		lc.setParent(item);
		lc = new Listcell(base.getModelo());
		lc.setParent(item);
		item.setAttribute("data", base);

	}

}
