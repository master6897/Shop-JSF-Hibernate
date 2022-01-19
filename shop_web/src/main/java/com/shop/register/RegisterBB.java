package com.shop.register;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import shop_dao.UserDAO;
import shop_entities.User;

@Named
@RequestScoped
public class RegisterBB {
	private static final String PAGE_LOGIN = "/pages/login";
	private static final String PAGE_REGISTER = "/pages/register/register?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String email;
	private String pass;
	private String pass_repeat;
	
	private User user = new User();
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass_repeat() {
		return pass_repeat;
	}

	public void setPass_repeat(String pass_repeat) {
		this.pass_repeat = pass_repeat;
	}
	public String register() {
		return PAGE_REGISTER;
	}
	public User getUser() {
		return user;
	}

	@EJB
	UserDAO userDAO;
	
	public String doRegister() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		Object exists = userDAO.getUserByEmail(email);
		
		if(exists != null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Użytkownik o podanym adresie email już istnieje!", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		if(pass.equals(pass_repeat)) {
			try {
				user.setEmail(email);
				user.setPass(pass);
				user.setRole("user");
				userDAO.create(user);
			}catch (Exception e) {
				e.printStackTrace();
				ctx.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coś poszło nie tak!", null));
				return PAGE_STAY_AT_THE_SAME;
			}
		}else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Hasła się nie zgadzają!", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		

		return PAGE_LOGIN;
	}
}
