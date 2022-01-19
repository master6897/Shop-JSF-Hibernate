package com.shop.shoe;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Query;

import shop_dao.QuantitySizeDAO;
import shop_entities.QuantitySize;

@Named
@RequestScoped
public class ShoeQuantityBB {

	@EJB
	QuantitySizeDAO quantitySizeDAO;
	private int id;
	
	 public List<QuantitySize> getList(Integer shoeId){
	    	List<QuantitySize> list = null;
	    	
	    	list = quantitySizeDAO.getList(shoeId);
	    	return list;
	    }
	 public QuantitySize find(Object id) {
	        return quantitySizeDAO.find(id);
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
