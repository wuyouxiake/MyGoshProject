package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GOSH_PRODUCT database table.
 * 
 */
@Entity
@Table(name="GOSH_PRODUCT", schema="TESTDB")
@NamedQuery(name="GoshProduct.findAll", query="SELECT g FROM GoshProduct g")
public class GoshProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GOSH_PRODUCT_PRODUCTID_GENERATOR", sequenceName="PRODUCT_SEQ",  allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOSH_PRODUCT_PRODUCTID_GENERATOR")
	@Column(name="PRODUCT_ID")
	private long productId;

	@Column(name="PRODUCT_DESC")
	private String productDesc;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_PHOTO")
	private String productPhoto;

	@Column(name="PRODUCT_PRICE")
	private double productPrice;

	@Column(name="PRODUCT_QTY")
	private int productQty;

	@Column(name="PRODUCT_SHIP")
	private double productShip;

	//bi-directional many-to-one association to GoshCart
	@OneToMany(mappedBy="goshProduct")
	private List<GoshCart> goshCarts;

	//bi-directional many-to-one association to GoshOrder
	@OneToMany(mappedBy="goshProduct")
	private List<GoshOrder> goshOrders;

	//bi-directional many-to-one association to GoshShop
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
	private GoshShop goshShop;

	public GoshProduct() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPhoto() {
		return this.productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}

	public double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return this.productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getProductShip() {
		return this.productShip;
	}

	public void setProductShip(double productShip) {
		this.productShip = productShip;
	}

	public List<GoshCart> getGoshCarts() {
		return this.goshCarts;
	}

	public void setGoshCarts(List<GoshCart> goshCarts) {
		this.goshCarts = goshCarts;
	}

	public GoshCart addGoshCart(GoshCart goshCart) {
		getGoshCarts().add(goshCart);
		goshCart.setGoshProduct(this);

		return goshCart;
	}

	public GoshCart removeGoshCart(GoshCart goshCart) {
		getGoshCarts().remove(goshCart);
		goshCart.setGoshProduct(null);

		return goshCart;
	}

	public List<GoshOrder> getGoshOrders() {
		return this.goshOrders;
	}

	public void setGoshOrders(List<GoshOrder> goshOrders) {
		this.goshOrders = goshOrders;
	}

	public GoshOrder addGoshOrder(GoshOrder goshOrder) {
		getGoshOrders().add(goshOrder);
		goshOrder.setGoshProduct(this);

		return goshOrder;
	}

	public GoshOrder removeGoshOrder(GoshOrder goshOrder) {
		getGoshOrders().remove(goshOrder);
		goshOrder.setGoshProduct(null);

		return goshOrder;
	}

	public GoshShop getGoshShop() {
		return this.goshShop;
	}

	public void setGoshShop(GoshShop goshShop) {
		this.goshShop = goshShop;
	}

}