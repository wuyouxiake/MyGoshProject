package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GOSH_CART database table.
 * 
 */
@Entity
@Table(name="GOSH_CART", schema="TESTDB")
@NamedQuery(name="GoshCart.findAll", query="SELECT g FROM GoshCart g")
public class GoshCart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GOSH_CART_CARTID_GENERATOR", sequenceName="SHOPPING_CART_SEQ",  allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOSH_CART_CARTID_GENERATOR")
	@Column(name="CART_ID")
	private long cartId;

	@Column(name="PRODUCT_QTY")
	private int productQty;

	@Column(name="SUB_TOTAL")
	private double subTotal;

	//bi-directional many-to-one association to GoshProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private GoshProduct goshProduct;

	//bi-directional many-to-one association to GoshUser
	@ManyToOne
	@JoinColumn(name="USER_EMAIL")
	private GoshUser goshUser;

	public GoshCart() {
	}

	public long getCartId() {
		return this.cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getProductQty() {
		return this.productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public GoshProduct getGoshProduct() {
		return this.goshProduct;
	}

	public void setGoshProduct(GoshProduct goshProduct) {
		this.goshProduct = goshProduct;
	}

	public GoshUser getGoshUser() {
		return this.goshUser;
	}

	public void setGoshUser(GoshUser goshUser) {
		this.goshUser = goshUser;
	}

}