package com.shop.shoe;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import shop_dao.CategoryDAO;
import shop_dao.ShoeDAO;
import shop_entities.Brand;
import shop_entities.Category;
import shop_entities.Shoe;

@Named
@RequestScoped
public class ShoeAddBB {
	
	private static final String PAGE_SHOE_LIST = "/pages/shoe/shoeList.xhtml";
	private int brand;
	private int category;
	private String name;
	private int price;
	private String descr;
	private String color;
	private String picture;
	
	@EJB
	ShoeDAO shoeDAO;
	public String addShoe() {
		try {
			shoeDAO.addShoe(brand, category, name, price, color, picture, descr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return PAGE_SHOE_LIST;
	}
	
	
	
	//gettery i settery
	public int getBrand() {
		return brand;
	}
	public void setBrand(int brand) {
		this.brand = brand;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
