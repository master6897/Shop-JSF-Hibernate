package com.shop.login;

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
public class LoginBB {
	private static final String PAGE_MAIN = "/pages/shoe/shoeList?faces-redirect=true";
	private static final String PAGE_LOGIN = "/pages/login";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String email;
	private String pass;

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

	@EJB
	UserDAO userDAO;

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		User user = userDAO.getUserFromDatabase(email, pass);

		// 2. if bad login or password - stay with error info
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub haslo", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		// 3. if logged in: get User roles, save in RemoteClient and store it in session
		
		RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
		client.setDetails(user);
		
		List<String> roles = userDAO.getUserRolesFromDatabase(user); //get User roles 
		
		if (roles != null) { //save roles in RemoteClient
			for (String role: roles) {
				client.getRoles().add(role);
			}
		}
	
		//store RemoteClient with request info in session (needed for SecurityFilter)
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		// and enter the system (now SecurityFilter will pass the request)
		return PAGE_MAIN;
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		return PAGE_LOGIN;
	}
	
}
