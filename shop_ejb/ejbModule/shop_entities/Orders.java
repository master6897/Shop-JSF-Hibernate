package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@NamedQuery(name="Orders.findAll", query="SELECT o FROM Orders o")
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String adres;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to TempOrder
	@OneToMany(mappedBy="order")
	private List<TempOrder> tempOrders;

	public Orders() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdres() {
		return this.adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TempOrder> getTempOrders() {
		return this.tempOrders;
	}

	public void setTempOrders(List<TempOrder> tempOrders) {
		this.tempOrders = tempOrders;
	}

	public TempOrder addTempOrder(TempOrder tempOrder) {
		getTempOrders().add(tempOrder);
		tempOrder.setOrder(this);

		return tempOrder;
	}

	public TempOrder removeTempOrder(TempOrder tempOrder) {
		getTempOrders().remove(tempOrder);
		tempOrder.setOrder(null);

		return tempOrder;
	}

}