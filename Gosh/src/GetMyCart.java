import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.GoshCart;
import model.GoshProduct;
import model.GoshShop;
import model.GoshUser;




/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetMyCart")
public class GetMyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMyCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(true);
//MMMMMMMMMMMODIFY			
			GoshUser User = (GoshUser) session.getAttribute("User");
			String myCart = "";
//get the number of transaction			
			//TypedQuery<Long> query1 = em.createQuery("SELECT count(u) FROM UserProd u WHERE u.userId = :userid",Long.class);
			//query1.setParameter("userid", userid);
			//count= query1.getSingleResult();
			double grandTotal = 0;
			double grandTax = 0;
			String fullList="";
			String qString = "SELECT g FROM GoshCart g WHERE g.goshUser = ?1";
			TypedQuery<GoshCart> query = DBUtil.createQuery(qString, GoshCart.class);
			query.setParameter(1, User);
			List<GoshCart> carts;
			try{
				carts=query.getResultList();
				if(carts ==null ||carts.isEmpty()){
					carts=null;
					String alert = "No items added!";
					// Set response content type
					response.setContentType("text/html");
					request.setAttribute("alert", alert);
					getServletContext().getRequestDispatcher("/MyCart.jsp").include(
							request, response);
					alert = "";
				}else{
					for (int i = 0; i < carts.size(); i++) {
						GoshProduct product = carts.get(i).getGoshProduct();
						//TypedQuery<Product> query3 = em.createQuery("SELECT p FROM Product p WHERE p.id = :productid",Product.class);
						//query3.setParameter("productid", productid);
						//Product p = query3.getSingleResult();
						String product_name = product.getProductName();
						String product_photo = product.getProductPhoto();
						String product_desc = product.getProductDesc();
						long qty = carts.get(i).getProductQty();
						double price = product.getProductPrice();
						double shipping = product.getProductShip();
						long product_id = product.getProductId();
						double thisTax = qty*price*0.06;
						double thisSubtotal = qty*price+thisTax;
						/*fullList+="<li class=\"list-group-item\"><a href=\"GetProductDetail?id="
			            		+product_id+"\">"+product_name+"</a><br>  "
			            		+"<b>Price: $"+price+"</b><br>"
			            		+"Qty: "+qty+"<br>"
			            		+"Tax: $"+thisTax+"<br>"
			            		+"Subtotal: $"+thisSubtotal+"<br>"
			            		+"</li>";*/
			            grandTotal+=thisSubtotal;
			            grandTax+=thisTax;
						
						myCart += "<li class=\"list-group-item\"><form class=\"form-horizontal\" role=\"form\" method=\"get\" action=\"UpdateCart\">"
								+ "<input type=\"hidden\" name=\"cartid\" value=\""+carts.get(i).getCartId()+"\"><img src=\""
										+ product_photo
										+ "\" style=\"width:120px;height:120px\"> <a href=\"GetProductDetail?id="
										+ product_id
										+ "\">" + product_name + "</a><br>  "
										+ "<b>Price: $" + price
										+ "<br>" + "Qty: <input type=\"number\" name=\"quantity\" value=\""+qty+"\" max=\""+product.getProductQty()+"\"><input type=\"submit\" name=\"submit\" value=\"Update\">"
										+ "<br>" + "Shipping: "+shipping
										+ "</b><br></form></li>";
					}
					String taxTotal = new DecimalFormat("#.##").format(grandTax);
					String fullTotal = new DecimalFormat("#.##").format(grandTotal);
					// Set response content type
					response.setContentType("text/html");

					request.setAttribute("myCart", myCart);
					//request.setAttribute("fullList", fullList);
					request.setAttribute("grandTotal", fullTotal);
					request.setAttribute("grandTax", taxTotal);
					getServletContext().getRequestDispatcher("/MyCart.jsp")
						.forward(request, response);
					myCart = "";
				}
			}catch(Exception e){
				System.out.println(e);
			}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	
}

