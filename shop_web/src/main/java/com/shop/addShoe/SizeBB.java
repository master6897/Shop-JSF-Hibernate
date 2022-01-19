package com.shop.addShoe;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.SizeDAO;

@Named
@RequestScoped
public class SizeBB {

	@EJB
	SizeDAO sizeDAO;
	
	public List getSizeList() {
		List list = null;
		list = sizeDAO.getSizeList();
		return list;
		
	}
}
