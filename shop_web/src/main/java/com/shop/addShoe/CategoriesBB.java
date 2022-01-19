package com.shop.addShoe;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.CategoryDAO;

@Named
@RequestScoped
public class CategoriesBB {
	private static final String PAGE_SHOE_LIST = "/pages/shoe/shoeList.xhtml";
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@EJB
	CategoryDAO categoryDAO;
	
	public List getCategories() {
		List list = null;
		list = categoryDAO.getCategories();
		return list;
	}
	
	public String addCategory() {
		categoryDAO.addCategory(name);
		return PAGE_SHOE_LIST;
	}
}
