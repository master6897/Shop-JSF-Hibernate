package com.shop.shoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.ShoeDAO;
import shop_entities.Shoe;


@Named
@RequestScoped
public class ShoeListBB{
	private String name;
	
	@EJB
	ShoeDAO shoeDAO;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Shoe> getFullList(){
		return shoeDAO.getFullList();
	}
	
	public List<Shoe> getList(){
		List<Shoe> list = null;
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if(name != null && name.length()>0) {
			searchParams.put("name", name);
		}
		
		list = shoeDAO.getList(searchParams);
		return list;
	}

	   
}
