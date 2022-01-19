package com.shop.deliveryAdress;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Query;

import shop_dao.DeliveryAdressDAO;
import shop_entities.DeliveryAdress;
import shop_entities.User;
@Named
@RequestScoped
public class DeliveryAdressBB {
	private static final String PAGE_ADRESS = "/pages/user/deliveryAdress.xhtml";
	private static final String PAGE_USER = "/pages/user/description.xhtml";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private DeliveryAdress delivery = new DeliveryAdress();
	public DeliveryAdress getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliveryAdress delivery) {
		this.delivery = delivery;
	}
	@EJB
	DeliveryAdressDAO deliveryAdressDAO;
	
	public String newAdress() {
		return PAGE_ADRESS;
	}
	
	public String addNewAdress(User user) {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Object exists = deliveryAdressDAO.getAdressesName(user.getId(), delivery.getName());
			if(exists == null){
				delivery.setUser(user);
				deliveryAdressDAO.create(delivery);
			}else {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Nazwy nie mogą się powtarzać!", null));
				return PAGE_STAY_AT_THE_SAME;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return PAGE_USER;
	}
	
	public List getAdresses(Integer userId){
    	List list = null;
    	try {
    		list = deliveryAdressDAO.getAdresses(userId);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
	public String deleteAdress(DeliveryAdress delivery) {
    	try {
    		deliveryAdressDAO.remove(delivery);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return PAGE_USER;
    }
}
