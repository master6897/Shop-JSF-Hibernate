package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shoe database table.
 * 
 */
@Entity
@NamedQuery(name="Shoe.findAll", query="SELECT s FROM Shoe s")
public class Shoe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String color;

	@Lob
	private String descr;

	private String name;

	private String picture;

	private int price;

	//bi-directional many-to-one association to Picture
	@OneToMany(mappedBy="shoe")
	private List<Picture> pictures;

	//bi-directional many-to-one association to QuantitySize
	@OneToMany(mappedBy="shoe")
	private List<QuantitySize> quantitySizes;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	private Brand brand;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to TempOrder
	@OneToMany(mappedBy="shoe")
	private List<TempOrder> tempOrders;

	public Shoe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

	public List<Picture> getPictures() {
		return this.pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Picture addPicture(Picture picture) {
		getPictures().add(picture);
		picture.setShoe(this);

		return picture;
	}

	public Picture removePicture(Picture picture) {
		getPictures().remove(picture);
		picture.setShoe(null);

		return picture;
	}

	public List<QuantitySize> getQuantitySizes() {
		return this.quantitySizes;
	}

	public void setQuantitySizes(List<QuantitySize> quantitySizes) {
		this.quantitySizes = quantitySizes;
	}

	public QuantitySize addQuantitySize(QuantitySize quantitySize) {
		getQuantitySizes().add(quantitySize);
		quantitySize.setShoe(this);

		return quantitySize;
	}

	public QuantitySize removeQuantitySize(QuantitySize quantitySize) {
		getQuantitySizes().remove(quantitySize);
		quantitySize.setShoe(null);

		return quantitySize;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<TempOrder> getTempOrders() {
		return this.tempOrders;
	}

	public void setTempOrders(List<TempOrder> tempOrders) {
		this.tempOrders = tempOrders;
	}

	public TempOrder addTempOrder(TempOrder tempOrder) {
		getTempOrders().add(tempOrder);
		tempOrder.setShoe(this);

		return tempOrder;
	}

	public TempOrder removeTempOrder(TempOrder tempOrder) {
		getTempOrders().remove(tempOrder);
		tempOrder.setShoe(null);

		return tempOrder;
	}

}