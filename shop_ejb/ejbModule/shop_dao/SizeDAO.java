package shop_dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SizeDAO {
private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
	
	public List getSizeList() {
		List list = null;
		Query query = em.createQuery("SELECT s FROM Size s");
		try {
			list = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
