package shop_dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class BrandDAO {
	private static final String UNIT_NAME = "shop";
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
	
	public List getBrands() {
		List list = null;
		Query query = em.createQuery("Select b from Brand b");
		try {
			list = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addBrand(String name) {
		Query query = em.createNativeQuery("Insert into brand(name) values(?)");
		query.setParameter(1, name);
		try {
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
