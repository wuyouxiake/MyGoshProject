package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GOSH_USER database table.
 * 
 */
@Entity
@Table(name="GOSH_USER", schema="TESTDB")
@NamedQuery(name="GoshUser.findAll", query="SELECT g FROM GoshUser g")
public class GoshUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_EMAIL")
	private String userEmail;

	private String fullname;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	private String username;

	//bi-directional many-to-one association to GoshCart
	@OneToMany(mappedBy="goshUser")
	private List<GoshCart> goshCarts;

	//bi-directional many-to-one association to GoshOrder
	@OneToMany(mappedBy="goshUser")
	private List<GoshOrder> goshOrders;

	//bi-directional many-to-one association to GoshShop
	@OneToMany(mappedBy="goshUser")
	private List<GoshShop> goshShops;

	public GoshUser() {
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<GoshCart> getGoshCarts() {
		return this.goshCarts;
	}

	public void setGoshCarts(List<GoshCart> goshCarts) {
		this.goshCarts = goshCarts;
	}

	public GoshCart addGoshCart(GoshCart goshCart) {
		getGoshCarts().add(goshCart);
		goshCart.setGoshUser(this);

		return goshCart;
	}

	public GoshCart removeGoshCart(GoshCart goshCart) {
		getGoshCarts().remove(goshCart);
		goshCart.setGoshUser(null);

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
		goshOrder.setGoshUser(this);

		return goshOrder;
	}

	public GoshOrder removeGoshOrder(GoshOrder goshOrder) {
		getGoshOrders().remove(goshOrder);
		goshOrder.setGoshUser(null);

		return goshOrder;
	}

	public List<GoshShop> getGoshShops() {
		return this.goshShops;
	}

	public void setGoshShops(List<GoshShop> goshShops) {
		this.goshShops = goshShops;
	}

	public GoshShop addGoshShop(GoshShop goshShop) {
		getGoshShops().add(goshShop);
		goshShop.setGoshUser(this);

		return goshShop;
	}

	public GoshShop removeGoshShop(GoshShop goshShop) {
		getGoshShops().remove(goshShop);
		goshShop.setGoshUser(null);

		return goshShop;
	}

}