package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int adress;

	private int adress2;

	private String city;

	private String email;

	private String pass;

	@Column(name="post_code")
	private String postCode;

	private String role;

	private String street;

	//bi-directional many-to-one association to DeliveryAdress
	@OneToMany(mappedBy="user")
	private List<DeliveryAdress> deliveryAdresses;

	//bi-directional many-to-one association to Orders
	@OneToMany(mappedBy="user")
	private List<Orders> orders;

	//bi-directional many-to-one association to TempOrder
	@OneToMany(mappedBy="user")
	private List<TempOrder> tempOrders;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdress() {
		return this.adress;
	}

	public void setAdress(int adress) {
		this.adress = adress;
	}

	public int getAdress2() {
		return this.adress2;
	}

	public void setAdress2(int adress2) {
		this.adress2 = adress2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<DeliveryAdress> getDeliveryAdresses() {
		return this.deliveryAdresses;
	}

	public void setDeliveryAdresses(List<DeliveryAdress> deliveryAdresses) {
		this.deliveryAdresses = deliveryAdresses;
	}

	public DeliveryAdress addDeliveryAdress(DeliveryAdress deliveryAdress) {
		getDeliveryAdresses().add(deliveryAdress);
		deliveryAdress.setUser(this);

		return deliveryAdress;
	}

	public DeliveryAdress removeDeliveryAdress(DeliveryAdress deliveryAdress) {
		getDeliveryAdresses().remove(deliveryAdress);
		deliveryAdress.setUser(null);

		return deliveryAdress;
	}

	public List<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Orders addOrder(Orders order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Orders removeOrder(Orders order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public List<TempOrder> getTempOrders() {
		return this.tempOrders;
	}

	public void setTempOrders(List<TempOrder> tempOrders) {
		this.tempOrders = tempOrders;
	}

	public TempOrder addTempOrder(TempOrder tempOrder) {
		getTempOrders().add(tempOrder);
		tempOrder.setUser(this);

		return tempOrder;
	}

	public TempOrder removeTempOrder(TempOrder tempOrder) {
		getTempOrders().remove(tempOrder);
		tempOrder.setUser(null);

		return tempOrder;
	}

}