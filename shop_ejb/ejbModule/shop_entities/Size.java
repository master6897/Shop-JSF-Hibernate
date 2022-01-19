package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the size database table.
 * 
 */
@Entity
@NamedQuery(name="Size.findAll", query="SELECT s FROM Size s")
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int value;

	//bi-directional many-to-one association to QuantitySize
	@OneToMany(mappedBy="size")
	private List<QuantitySize> quantitySizes;

	public Size() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<QuantitySize> getQuantitySizes() {
		return this.quantitySizes;
	}

	public void setQuantitySizes(List<QuantitySize> quantitySizes) {
		this.quantitySizes = quantitySizes;
	}

	public QuantitySize addQuantitySize(QuantitySize quantitySize) {
		getQuantitySizes().add(quantitySize);
		quantitySize.setSize(this);

		return quantitySize;
	}

	public QuantitySize removeQuantitySize(QuantitySize quantitySize) {
		getQuantitySizes().remove(quantitySize);
		quantitySize.setSize(null);

		return quantitySize;
	}

}