package com.shop.cart;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import shop_dao.CartDAO;
import shop_entities.QuantitySize;
import shop_entities.Shoe;
import shop_entities.Size;
import shop_entities.TempOrder;

@Named
@RequestScoped
public class CartBB {
	private static final String PAGE_SHOE_LIST = "/pages/shoe/shoeList.xhtml";
	private static final String PAGE_CART = "/pages/cart/cart.xhtml";
	private static final String PAGE_ORDER = "/pages/order/order.xhtml";
	
	@Inject
	FacesContext context;
	
	@EJB
	CartDAO cartDAO;
	
	private int size;
	private int count = 0;
	private int quantity = 1;
	private int price = 0;
	private int tempQuan;
	private QuantitySize objectQuantity = new QuantitySize();
	private TempOrder tempOrder = new TempOrder();
	
	public int getTempQuan() {
		return tempQuan;
	}

	public void setTempQuan(int tempQuan) {
		this.tempQuan = tempQuan;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String add(Integer shoe_id, Integer user_id) {
		size = getSize();
		try {
			if(quantity <= checkQuantity()) {
				System.out.println(quantity);
				cartDAO.insertIntoCart( shoe_id, size, user_id, quantity);
				setSize(size);
				setTempQuan(checkQuantity() - quantity);
				objectQuantity.setQuantity(tempQuan);
				cartDAO.mergeQuantity(objectQuantity);
			}else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie posiadamy takiej ilości butów na stanie", null));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produkt dodany do koszyka", null));
		return PAGE_SHOE_LIST;
	}
	
	public String addQuantity(TempOrder tempOrder, Integer size) {
		setSize(size);
		size = getSize();
		System.out.println(size);
		try {
			setQuantity(tempOrder.getOrderQuantity());
			if(checkQuantity() >=1) {
				tempOrder.setOrderQuantity(quantity+1);
				setTempQuan(checkQuantity() - 1);
				objectQuantity.setQuantity(tempQuan);
				cartDAO.mergeQuantity(objectQuantity);
				cartDAO.merge(tempOrder);
			}else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Niepoprawna ilość","Nie posiadamy takiej ilości butów na stanie"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return PAGE_CART;
	}
	public String minusQuantity(TempOrder tempOrder, Integer size) {
		setSize(size);
		size = getSize();
		System.out.println(size);
		try {
			quantity = tempOrder.getOrderQuantity();
			if(quantity>=2) {
				tempOrder.setOrderQuantity(quantity-1);
				setTempQuan(checkQuantity() +1);
				objectQuantity.setQuantity(tempQuan);
				cartDAO.mergeQuantity(objectQuantity);
				cartDAO.merge(tempOrder);
			}else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Nie zamawiasz?","Prosz� usun�� produkt z koszyka"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return PAGE_CART;
	}
	
	public List getCart(Integer user_id) {
		List list = null;
		try {
			list= cartDAO.getCart(user_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int count(Integer user_id) {
		try {
			count = cartDAO.count(user_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public String deleteItem(TempOrder tempOrder, Shoe shoe, Size sizeOb, Integer quanId, Integer quanValue, Integer orderQuan) {
		try {
			cartDAO.remove(tempOrder);
			int tempQuan2 = quanValue + orderQuan;
			objectQuantity.setId(quanId);
			objectQuantity.setQuantity(tempQuan2);
			objectQuantity.setShoe(shoe);
			objectQuantity.setSize(sizeOb);
			cartDAO.mergeQuantity(objectQuantity);
		}catch(Exception e) {
			e.printStackTrace();
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produkt usuni�ty z koszyka", null));
		return PAGE_SHOE_LIST;
	}
	
	public int getPrice(Integer itemPrice, Integer itemQuantity) {
		int tempPrice = itemPrice*itemQuantity;
		price+=tempPrice;
		return price;
	}
	
	public int checkQuantity() {
			try {
				objectQuantity = cartDAO.findQuantity(size);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return objectQuantity.getQuantity();
	}
	
	public String order() {
		return PAGE_ORDER;
	}
	
	public boolean disabled(Integer quantity) {
		if(quantity == 0) {
			return true;
		}else {
			return false;
		}
	}

	
}
