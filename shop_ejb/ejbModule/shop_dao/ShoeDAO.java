package shop_dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import shop_entities.Shoe;

@Stateless
public class ShoeDAO {
	private final static String UNIT_NAME = "shop";
	
	@PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Shoe shoe) {
        em.persist(shoe);
    }

    public Shoe update(Shoe shoe) {
        return em.merge(shoe);
    }

    public void delete(Shoe shoe) {
        em.remove(em.merge(shoe));
    }

    public Shoe find(Object id) {
        return em.find(Shoe.class, id);
    }
    
    public List<Shoe> getFullList(){
    	List<Shoe> list = null;
    	
    	Query query = em.createQuery("select s from Shoe s");
    	try {
    		list = query.getResultList();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public List<Shoe> getList(Map<String, Object> searchParams){
    	List<Shoe> list = null;
    	
    	String select = "SELECT s ";
    	String from = "from Shoe s ";
    	String where ="";
    	
    	String name = (String) searchParams.get("name");
    	if(name != null) {
    		if(where.isEmpty()) {
    			where= "where ";
    		}else {
    			where+= "and ";
    		}
    		where+= "s.name like :name OR s.brand.name like :name ";
    	}
    	
    	Query query = em.createQuery(select + from + where );
    	
    	if(name !=null) {
    		query.setParameter("name", "%"+name+"%");
    	}
    	
    	try {
    		list = query.getResultList();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public void addShoe(Integer brandId, Integer categoryId, String name, Integer price, String color, String picture,String descr) {
    	Query query = em.createNativeQuery("Insert into shoe(name,price,descr,color,picture,brand_id,category_id) values(?,?,?,?,?,?,?)");
    	query.setParameter(1, name);
    	query.setParameter(2, price);
    	query.setParameter(3, descr);
    	query.setParameter(4, color);
    	query.setParameter(5, picture);
    	query.setParameter(6, brandId);
    	query.setParameter(7, categoryId);
    	
    	try {
    		query.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
