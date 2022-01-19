package shop_entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pictures database table.
 * 
 */
@Entity
@Table(name="pictures")
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] picture;

	//bi-directional many-to-one association to Shoe
	@ManyToOne
	private Shoe shoe;

	public Picture() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Shoe getShoe() {
		return this.shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

}