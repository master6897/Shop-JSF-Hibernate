package shop_dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CategoryDAO {
	private static final String UNIT_NAME = "shop";
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
	
	public List getCategories() {
		List list = null;
		Query query = em.createQuery("Select c from Category c");
		try {
			list = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addCategory(String name) {
		Query query = em.createNativeQuery("Insert into category(cat_name) values(?)");
		query.setParameter(1, name);
		try {
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
