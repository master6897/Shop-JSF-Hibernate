package shop_dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AdressesDAO {

	private final static String UNIT_NAME = "shop";
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
	
	public List getAdresses(Integer userId, String name) {
		List list = null;
		Query query = em.createQuery("Select d from DeliveryAdress d where user_id=:userId AND name=:name");
		query.setParameter("userId", userId);
		query.setParameter("name", name);
		try {
			list = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
