package shop_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the brand database table.
 * 
 */
@Entity
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Shoe
	@OneToMany(mappedBy="brand")
	private List<Shoe> shoes;

	public Brand() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shoe> getShoes() {
		return this.shoes;
	}

	public void setShoes(List<Shoe> shoes) {
		this.shoes = shoes;
	}

	public Shoe addShoe(Shoe shoe) {
		getShoes().add(shoe);
		shoe.setBrand(this);

		return shoe;
	}

	public Shoe removeShoe(Shoe shoe) {
		getShoes().remove(shoe);
		shoe.setBrand(null);

		return shoe;
	}

}