import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import model.GoshCart;
import model.GoshProduct;
import model.GoshUser;
import customTools.DBUtil;



/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		GoshUser user = (GoshUser) session.getAttribute("User");
		long productId = Long.parseLong(request.getParameter("productId"));
		GoshProduct product = lookUpProduct(productId);
		int qty =Integer.parseInt(request.getParameter("quantity"));
		
		
		
			
			String qString = "select count(g) from GoshCart g where g.goshUser=?1 and g.goshProduct=?2";
			TypedQuery<Long> q = DBUtil.createQuery(qString, Long.class);
			q.setParameter(1, user);
			q.setParameter(2, product);
			
			GoshCart goshCart=new GoshCart();
			
			if(q.getSingleResult()==0){
				goshCart.setGoshProduct(product);
				goshCart.setGoshUser(user);
				goshCart.setProductQty(qty);
				double thisSubtotal = product.getProductPrice()*qty;
				goshCart.setSubTotal(thisSubtotal);
				GoshCartDB.insert(goshCart);
				String alert = "Added to cart!";
				request.setAttribute("alert", alert);
				getServletContext().getRequestDispatcher("/GetAll?All=products")
						.forward(request, response);
			}else{
				String qString2= "select g from GoshCart g where g.goshUser=?1 and g.goshProduct=?2";
				TypedQuery<GoshCart> q2 = DBUtil.createQuery(qString2, GoshCart.class);
				q2.setParameter(1, user);
				q2.setParameter(2, product);
				goshCart=q2.getSingleResult();
				goshCart.setProductQty(goshCart.getProductQty()+qty);
				GoshCartDB.update(goshCart);
				String alert = "Added to cart!";
				request.setAttribute("alert", alert);
				getServletContext().getRequestDispatcher("/GetAll?All=products")
						.forward(request, response);
			}
		
	}
	
	protected model.GoshProduct lookUpProduct(long productid){
		TypedQuery<model.GoshProduct> gQuery = DBUtil.createQuery("Select g from GoshProduct g where g.productId = :productId", model.GoshProduct.class).setParameter("productId", productid);
		return gQuery.getSingleResult();
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