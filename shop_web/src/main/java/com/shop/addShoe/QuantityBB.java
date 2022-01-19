package com.shop.addShoe;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.QuantitySizeDAO;

@Named
@RequestScoped
public class QuantityBB {
	private static final String PAGE_SHOE_LIST = "/pages/shoe/shoeList.xhtml";
	private static final String PAGE_SHOE_SIZE = "/pages/shoe/addShoeSize.xhtml";
	private int shoeId;
	private int quantity;
	private int sizeId;
	public int getShoeId() {
		return shoeId;
	}
	public void setShoeId(int shoeId) {
		this.shoeId = shoeId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	@EJB
	QuantitySizeDAO quantitySizeDAO;
	
	public String addSizeQuantity() {
		getSizeId();
		getShoeId();
		getQuantity();
		quantitySizeDAO.addSizeQuantity(quantity, shoeId, sizeId);
		return PAGE_SHOE_LIST;
	}
	
	
	
}
