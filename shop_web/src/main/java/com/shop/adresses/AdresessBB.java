package com.shop.adresses;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.AdressesDAO;

@Named
@RequestScoped
public class AdresessBB {

	@EJB
	AdressesDAO adressesDAO;
	
	public List getAdresses(Integer userId, String name) {
		List list = null;
		list = adressesDAO.getAdresses(userId, name);
		return list;
	}
}
