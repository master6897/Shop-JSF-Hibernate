package com.shop.shoe;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import shop_entities.QuantitySize;
import shop_entities.Shoe;
import shop_dao.QuantitySizeDAO;
import shop_dao.ShoeDAO;

@Named
@ViewScoped
public class ShoeDescriptionGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_SHOE_LIST = "shoeList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Shoe shoe = new Shoe();
	private QuantitySize quantitySize = new QuantitySize();
	private Shoe loaded = null;

	@EJB
	ShoeDAO shoeDAO;
	QuantitySizeDAO quantitySizeDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Shoe getShoe() {
		return shoe;
	}


	public QuantitySize getQuantitySize() {
		return quantitySize;
	}

	public void setQuantitySize(QuantitySize quantitySize) {
		this.quantitySize = quantitySize;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && shoe.getId() != 0) {
				loaded = shoeDAO.find(shoe.getId());
			}
			if (loaded != null) {
				shoe = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}
}
