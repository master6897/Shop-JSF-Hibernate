package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the quantity_size database table.
 * 
 */
@Entity
@Table(name="quantity_size")
@NamedQuery(name="QuantitySize.findAll", query="SELECT q FROM QuantitySize q")
public class QuantitySize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int quantity;

	//bi-directional many-to-one association to Shoe
	@ManyToOne
	private Shoe shoe;

	//bi-directional many-to-one association to Size
	@ManyToOne
	private Size size;

	//bi-directional many-to-one association to TempOrder
	@OneToMany(mappedBy="quantitySize")
	private List<TempOrder> tempOrders;

	public QuantitySize() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Shoe getShoe() {
		return this.shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public List<TempOrder> getTempOrders() {
		return this.tempOrders;
	}

	public void setTempOrders(List<TempOrder> tempOrders) {
		this.tempOrders = tempOrders;
	}

	public TempOrder addTempOrder(TempOrder tempOrder) {
		getTempOrders().add(tempOrder);
		tempOrder.setQuantitySize(this);

		return tempOrder;
	}

	public TempOrder removeTempOrder(TempOrder tempOrder) {
		getTempOrders().remove(tempOrder);
		tempOrder.setQuantitySize(null);

		return tempOrder;
	}

}