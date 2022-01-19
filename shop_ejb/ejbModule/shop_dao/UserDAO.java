package shop_dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop_entities.User;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public User merge(User user) {
        return em.merge(user);
    }

    public void remove(User user) {
        em.remove(em.merge(user));
    }

    public User find(Object id) {
        return em.find(User.class, id);
    }
    
	public User getUserFromDatabase(String email, String pass) {
		User u = null;
		Query query = em.createQuery("select u from User u WHERE u.email = :email AND u.pass = :pass");
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		try {
			u = (User)query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return u;
		}
	public List<String> getUserRolesFromDatabase(User user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		roles.add(user.getRole());
		
		return roles;
	}
	
	public User getUserByEmail(String email) {
		User u = null;
		Query query = em.createQuery("select u from User u where u.email = :email");
		query.setParameter("email", email);
		
		try {
			u = (User)query.getSingleResult();
		} catch(Exception e) {
			return u;
		}
		return u;
	}
}
