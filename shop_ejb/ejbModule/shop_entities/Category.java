package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cat_name")
	private String catName;

	//bi-directional many-to-one association to Shoe
	@OneToMany(mappedBy="category")
	private List<Shoe> shoes;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public List<Shoe> getShoes() {
		return this.shoes;
	}

	public void setShoes(List<Shoe> shoes) {
		this.shoes = shoes;
	}

	public Shoe addShoe(Shoe shoe) {
		getShoes().add(shoe);
		shoe.setCategory(this);

		return shoe;
	}

	public Shoe removeShoe(Shoe shoe) {
		getShoes().remove(shoe);
		shoe.setCategory(null);

		return shoe;
	}

}