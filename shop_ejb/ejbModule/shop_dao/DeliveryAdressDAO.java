package shop_dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop_entities.DeliveryAdress;

@Stateless
public class DeliveryAdressDAO {
private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(DeliveryAdress deliveryAdress) {
        em.persist(deliveryAdress);
    }

    public DeliveryAdress merge(DeliveryAdress deliveryAdress) {
        return em.merge(deliveryAdress);
    }

    public void remove(DeliveryAdress deliveryAdress) {
        em.remove(em.merge(deliveryAdress));
    }

    public DeliveryAdress find(Object id) {
        return em.find(DeliveryAdress.class, id);
    }
    
    public List getAdresses(Integer userId){
    	List list = null;
    	Query query = em.createQuery("Select d FROM DeliveryAdress d where user_id=:userId");
    	query.setParameter("userId", userId);
    	try {
    		list = query.getResultList();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public DeliveryAdress getAdressesName(Integer userId, String name){
    	DeliveryAdress d = null;
    	Query query = em.createQuery("Select d FROM DeliveryAdress d where user_id=:userId AND name=:name");
    	query.setParameter("userId", userId);
    	query.setParameter("name", name);
    	try {
    		d = (DeliveryAdress)query.getSingleResult();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return d;
    }
}
