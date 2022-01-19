package shop_dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop_entities.QuantitySize;
import shop_entities.TempOrder;


@Stateless

public class CartDAO {
	private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
	
	public void create(TempOrder tempOrder) {
        em.persist(tempOrder);
    }

    public TempOrder merge(TempOrder tempOrder) {
        return em.merge(tempOrder);
    }

    public void remove(TempOrder tempOrder) {
        em.remove(em.merge(tempOrder));
    }

    public TempOrder find(Object id) {
        return em.find(TempOrder.class, id);
    }
    
    public QuantitySize findQuantity(Object id) {
    	return em.find(QuantitySize.class, id);
    }
    
    public QuantitySize mergeQuantity(QuantitySize quantitySize) {
    	return em.merge(quantitySize);
    }
	
	public void insertIntoCart( Integer shoe_id, Integer quantity_size_id, Integer user, Integer quantity) {
		Query query = em.createNativeQuery("INSERT INTO Temp_Order(shoe_id, quantity_size_id, user_id, order_quantity) VALUES(?, ?, ?, ?)");
		query.setParameter(1, shoe_id);
		query.setParameter(2, quantity_size_id);
		query.setParameter(3, user);
		query.setParameter(4, quantity);		
		try {
			query.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List getCart(Integer user) {
		List list = null;
		Query query = em.createQuery("Select t from TempOrder t where status=0 AND user_id=:user");
		query.setParameter("user", user);
		try {
			list = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int count(Integer user) {
		List list = null;
		int count = 0;
		Query query = em.createQuery("Select t from TempOrder t where status=0 AND user_id=:user");
		query.setParameter("user", user);
		try {
			list = query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		count = list.size();
		return count;
	}
	
	public Object checkQuantity(int sizeId) {
		Object object = null;
		Query query = em.createQuery("Select q from QuantitySize q where id=:sizeID");
		query.setParameter(sizeId, "sizeId");
		try {
			object = query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
