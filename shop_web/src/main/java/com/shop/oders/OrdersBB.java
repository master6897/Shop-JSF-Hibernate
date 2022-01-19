package com.shop.oders;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import shop_dao.OrderDAO;
import shop_entities.Orders;

@Named
@RequestScoped
public class OrdersBB {
	private static final String PAGE_ORDER_DESCRIPTION = "/pages/user/orderDescription.xhtml";
	private List list2 = null;
	private int price = 0;
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public String details() {
		return PAGE_ORDER_DESCRIPTION;
	}

	public List getList2() {
		return list2;
	}

	public void setList2(List list2) {
		this.list2 = list2;
	}
@EJB
OrderDAO orderDAO;
	public List getOrders(Integer userId) {
		List list = null;
		list = orderDAO.getOrders(userId);
		return list;
	}
	
	public String getDetails(long orderId, Integer userId) {
		setList2(orderDAO.getDetails(orderId, userId));
		return PAGE_ORDER_DESCRIPTION;
		
	}
	public void getPrice(Integer itemPrice, Integer itemQuantity) {
		int tempPrice = itemPrice*itemQuantity;
		System.out.println(tempPrice);
		price+=tempPrice;
	}
}
