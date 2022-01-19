package shop_dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop_entities.Orders;
import shop_entities.TempOrder;

@Stateless
public class OrderDAO {

private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
	
	public void create(Orders orders) {
        em.persist(orders);
    }

    public Orders merge(Orders orders) {
        return em.merge(orders);
    }

    public void remove(Orders orders) {
        em.remove(em.merge(orders));
    }

    public Orders find(Object id) {
        return em.find(Orders.class, id);
    }
    
    public boolean updateTempOrder(Integer userId, long orderId) {
    	Query query = em.createNativeQuery("Update temp_order SET status = 1, order_id=:orderId WHERE user_id=:userId AND status=0");
    	query.setParameter("userId", userId);
    	query.setParameter("orderId", orderId);
    	try {
    		query.executeUpdate();
    		return true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    	
    }
    public void newOrder(Integer userId, long orderId, String adres) {
    	Query query = em.createNativeQuery("INSERT INTO orders (id,user_id,adres) VALUES(?,?,?)");
    	query.setParameter(1, orderId);
    	query.setParameter(2, userId);
    	query.setParameter(3, adres);
    	try {
    		query.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    public List getOrders(Integer userId) {
    	List list = null;
    	Query query = em.createQuery("Select o from Orders o where user_id= :userId order by id");
    	query.setParameter("userId", userId);
    	try {
    		list = query.getResultList();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public List getDetails(long orderId, Integer userId) {
    	List list = null;
    	Query query = em.createQuery("Select t from TempOrder t where order_id= :orderId AND user_id= :userId");
    	query.setParameter("orderId", orderId);
    	query.setParameter("userId", userId);
    	try {
    		list = query.getResultList();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
}
