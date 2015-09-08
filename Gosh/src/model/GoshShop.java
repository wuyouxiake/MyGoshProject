package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GOSH_SHOP database table.
 * 
 */
@Entity
@Table(name="GOSH_SHOP", schema="TESTDB")
@NamedQuery(name="GoshShop.findAll", query="SELECT g FROM GoshShop g")
public class GoshShop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GOSH_SHOP_SHOPID_GENERATOR", sequenceName="SHOP_SEQ",  allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOSH_SHOP_SHOPID_GENERATOR")
	@Column(name="SHOP_ID")
	private long shopId;

	@Column(name="SHOP_DESC")
	private String shopDesc;

	@Column(name="SHOP_NAME")
	private String shopName;

	@Column(name="SHOP_PHOTO")
	private String shopPhoto;

	//bi-directional many-to-one association to GoshOrder
	@OneToMany(mappedBy="goshShop")
	private List<GoshOrder> goshOrders;

	//bi-directional many-to-one association to GoshProduct
	@OneToMany(mappedBy="goshShop")
	private List<GoshProduct> goshProducts;

	//bi-directional many-to-one association to GoshUser
	@ManyToOne
	@JoinColumn(name="USER_EMAIL")
	private GoshUser goshUser;

	public GoshShop() {
	}

	public long getShopId() {
		return this.shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopDesc() {
		return this.shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhoto() {
		return this.shopPhoto;
	}

	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

	public List<GoshOrder> getGoshOrders() {
		return this.goshOrders;
	}

	public void setGoshOrders(List<GoshOrder> goshOrders) {
		this.goshOrders = goshOrders;
	}

	public GoshOrder addGoshOrder(GoshOrder goshOrder) {
		getGoshOrders().add(goshOrder);
		goshOrder.setGoshShop(this);

		return goshOrder;
	}

	public GoshOrder removeGoshOrder(GoshOrder goshOrder) {
		getGoshOrders().remove(goshOrder);
		goshOrder.setGoshShop(null);

		return goshOrder;
	}

	public List<GoshProduct> getGoshProducts() {
		return this.goshProducts;
	}

	public void setGoshProducts(List<GoshProduct> goshProducts) {
		this.goshProducts = goshProducts;
	}

	public GoshProduct addGoshProduct(GoshProduct goshProduct) {
		getGoshProducts().add(goshProduct);
		goshProduct.setGoshShop(this);

		return goshProduct;
	}

	public GoshProduct removeGoshProduct(GoshProduct goshProduct) {
		getGoshProducts().remove(goshProduct);
		goshProduct.setGoshShop(null);

		return goshProduct;
	}

	public GoshUser getGoshUser() {
		return this.goshUser;
	}

	public void setGoshUser(GoshUser goshUser) {
		this.goshUser = goshUser;
	}

}