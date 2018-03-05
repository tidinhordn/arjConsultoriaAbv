package br.com.arjconsultoria.component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;


public class MyListbox<E> extends Listbox {

	private static final long serialVersionUID = 1L;
	private E selected = null;
    private ListModelList<E> modelList = new ListModelList<>();

    public void setData(List<E> data) {

        this.modelList.clear();
        this.modelList.addAll(data);
        
        super.setModel(modelList);
    }

    public MyListbox() {
        super.setModel(modelList);
        setVflex(Boolean.TRUE);
    }

    /**
     * Adiciona um item na lista caso ele não esteja presente na lista
     * @param item
     * @return - True se o item foi adicionado
     */
    public boolean addItem(E item) {
        boolean ok = false;
        if (!this.modelList.contains(item)) {
            ok = this.modelList.add(item);
        }

        return ok;
    }

    /**
     * Atualiza se o item está presente na lista. Caso não esteja presente, o item é adicionado.
     * @param item
     */
    public void updateItem(E item) {
        if (this.modelList.contains(item)) {
            this.modelList.set(modelList.indexOf(item), item);
        } else {
            this.addItem(item);
        }
    }

    public void removeItem(E item) {
        this.modelList.remove(item);
    }
    
    public E removeSelectedItem() {
    	int index = this.getSelectedIndex();
        selected = null;
        if (index != -1) {
            selected = this.modelList.get(index);
            this.modelList.remove(index);
        }
        return selected;
    }


    public E getSelected() {
        int index = this.getSelectedIndex();
        selected = null;
        if (index != -1) {
            selected = this.modelList.get(index);
        }

        return this.selected;
    }

    public List<E> getSelectedsItens() {

        int index = this.getSelectedIndex();
        int qtd = this.getSelectedCount();
        List<E> selecteds = new ArrayList<E>();
        if (index != -1) {
            for (int i = 0; i < qtd; i++) {
                selecteds.add(this.modelList.get(index + i));
            }
        }
        
        return selecteds;
    }

    public int getSize() {
        return (modelList == null) ? 0 : modelList.getSize();
    }

    @SuppressWarnings("unchecked")
	public E[] getData() {
        return (E[]) this.modelList.toArray();
    }
        
    @SuppressWarnings( "rawtypes" )
    public void removeAll(Collection c){
    	this.modelList.removeAll(c);
        }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addAll(Collection c){
    	this.modelList.addAll(c);
    }
    
    public List<E> getAllItens(){
    	return Arrays.asList(getData());
    }
    
    public void sort(Comparator<E> c){
    	this.modelList.sort(c);
    }
}


