package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the temp_order database table.
 * 
 */
@Entity
@Table(name="temp_order")
@NamedQuery(name="TempOrder.findAll", query="SELECT t FROM TempOrder t")
public class TempOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="order_quantity")
	private int orderQuantity;

	private int status;

	//bi-directional many-to-one association to Orders
	@ManyToOne
	private Orders order;

	//bi-directional many-to-one association to QuantitySize
	@ManyToOne
	@JoinColumn(name="quantity_size_id")
	private QuantitySize quantitySize;

	//bi-directional many-to-one association to Shoe
	@ManyToOne
	private Shoe shoe;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public TempOrder() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getOrderQuantity() {
		return this.orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Orders getOrder() {
		return this.order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public QuantitySize getQuantitySize() {
		return this.quantitySize;
	}

	public void setQuantitySize(QuantitySize quantitySize) {
		this.quantitySize = quantitySize;
	}

	public Shoe getShoe() {
		return this.shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}