package com.shop.addShoe;


import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.BrandDAO;
import shop_entities.Brand;

@Named
@RequestScoped
public class BrandsBB {
	private static final String PAGE_SHOE_LIST = "/pages/shoe/shoeList.xhtml";
	private String name;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@EJB
	BrandDAO brandDAO;
	public List getBrands() {
		List list = null;
		try {
			list = brandDAO.getBrands();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String addBrand() {
		brandDAO.addBrand(name);
		return PAGE_SHOE_LIST;
	}
}
