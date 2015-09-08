package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the GOSH_ORDERS database table.
 * 
 */
@Entity
@Table(name="GOSH_ORDERS", schema="TESTDB")
@NamedQuery(name="GoshOrder.findAll", query="SELECT g FROM GoshOrder g")
public class GoshOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GOSH_ORDERS_ORDERID_GENERATOR", sequenceName="ORDERING_SEQ",  allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GOSH_ORDERS_ORDERID_GENERATOR")
	@Column(name="ORDER_ID")
	private long orderId;

	@Column(name="ORDER_AMT")
	private double orderAmt;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	//bi-directional many-to-one association to GoshProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private GoshProduct goshProduct;

	//bi-directional many-to-one association to GoshShop
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
	private GoshShop goshShop;

	//bi-directional many-to-one association to GoshUser
	@ManyToOne
	@JoinColumn(name="USER_EMAIL")
	private GoshUser goshUser;

	public GoshOrder() {
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getOrderAmt() {
		return this.orderAmt;
	}

	public void setOrderAmt(double orderAmt) {
		this.orderAmt = orderAmt;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public GoshProduct getGoshProduct() {
		return this.goshProduct;
	}

	public void setGoshProduct(GoshProduct goshProduct) {
		this.goshProduct = goshProduct;
	}

	public GoshShop getGoshShop() {
		return this.goshShop;
	}

	public void setGoshShop(GoshShop goshShop) {
		this.goshShop = goshShop;
	}

	public GoshUser getGoshUser() {
		return this.goshUser;
	}

	public void setGoshUser(GoshUser goshUser) {
		this.goshUser = goshUser;
	}

}