package shop_dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop_entities.QuantitySize;

@Stateless
public class QuantitySizeDAO {
	private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(QuantitySize quas) {
        em.persist(quas);
    }

    public QuantitySize update(QuantitySize quas) {
        return em.merge(quas);
    }

    public void delete(QuantitySize quas) {
        em.remove(em.merge(quas));
    }

    public QuantitySize find(Object id) {
        return em.find(QuantitySize.class, id);
    }
    
    public List<QuantitySize> getFullList(){
    	List<QuantitySize> list = null;
    	
    	Query query = em.createQuery("select q from QuantitySize q");
    	try {
    		list = query.getResultList();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public List<QuantitySize> getList(Integer shoeId){
    	List<QuantitySize> list = null;
    	
    	
    	Query query = em.createQuery("select q from QuantitySize q where shoe_id = :shoeId");
    	query.setParameter("shoeId", shoeId);
    	
    	try {
    		list = query.getResultList();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public void addSizeQuantity(Integer quantity, Integer shoeId, Integer sizeId) {
    	Query query = em.createNativeQuery("INSERT into quantity_size(quantity, shoe_id, size_id) values(?,?,?)");
    	query.setParameter(1, quantity);
    	query.setParameter(2, shoeId);
    	query.setParameter(3, sizeId);
    	try {
    		query.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
