package shop_entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the delivery_adress database table.
 * 
 */
@Entity
@Table(name="delivery_adress")
@NamedQuery(name="DeliveryAdress.findAll", query="SELECT d FROM DeliveryAdress d")
public class DeliveryAdress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int adress;

	private int adress2;

	private String city;

	private String name;

	@Column(name="post_code")
	private String postCode;

	private String street;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public DeliveryAdress() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}