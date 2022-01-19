package com.shop.cart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import shop_dao.OrderDAO;
import shop_entities.Orders;
import shop_entities.User;

@Named
@RequestScoped
public class OrderBB {
	private static final String PAGE_ORDER_LIST = "/pages/user/orderList.xhtml?faces-redirect=true";
	private static final String PAGE_ORDER = "/pages/order/order.xhtml";
	
	private String adress;
	private long tempId;
	private boolean agreement;
	private Orders order = new Orders();
	@Inject
	FacesContext context;
	
	public boolean getAgreement() {
		return agreement;
	}
	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}
	

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@EJB
	OrderDAO orderDAO;
	
	public String getIdByDate() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String dateTime = ft.format(dNow);
		return dateTime;
	}
	
	public List decodeDate(long date2, Integer userId) {
		List list = null;
		try {
			list = orderDAO.getDetails(date2, userId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String createOrder(User user, Integer userId) {
		try {
			tempId = Long.parseLong(getIdByDate());
		}catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		if(agreement != false) {
			try {
				if(adress == null || adress.length() == 0) {
					try {
						orderDAO.newOrder(userId, tempId, "default");
						orderDAO.updateTempOrder(userId, tempId);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					try {
						orderDAO.newOrder(userId, tempId, adress);
						orderDAO.updateTempOrder(userId, tempId);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Proszę zaakceptować regulamin", null));
			return PAGE_ORDER;
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zamówienie przyjęte do relizacji", null));
		return PAGE_ORDER_LIST;
	}

}