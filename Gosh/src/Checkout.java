import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import model.GoshOrder;
import model.GoshProduct;
import model.GoshUser;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkout() {
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
		String fullList = "";
		// Get value from session and request
		GoshUser user = (GoshUser) session.getAttribute("User");
		String alert = "Thanks for shopping with us!";

		// Update Quantity
		String qString1 = "select g from GoshCart g where g.goshUser = ?1";
		TypedQuery<GoshCart> q1 = DBUtil.createQuery(qString1, GoshCart.class);
		q1.setParameter(1, user);
		List<GoshCart> updateList = q1.getResultList();
		for (int j = 0; j < updateList.size(); j++) {
			System.out.println("CheckOut " + updateList.get(j));
			GoshCart tempCart = updateList.get(j);
			int tempQty = tempCart.getProductQty();
			long tempId = tempCart.getGoshProduct().getProductId();

		//	String qString2 = "SELECT g FROM GoshProduct g where g.productId = ?1";
	//		TypedQuery<GoshProduct> q2 = DBUtil.createQuery(qString2,
	//				GoshProduct.class);
		//	q2.setParameter(1, tempId); 
			GoshProduct tempProduct = updateList.get(j).getGoshProduct();
			tempProduct.setProductQty(tempProduct.getProductQty() - tempQty);
			GoshProductDB.update(tempProduct);

			GoshOrder thisOrder = new GoshOrder();
			thisOrder.setGoshUser(user);
			thisOrder.setGoshProduct(updateList.get(j).getGoshProduct());
			thisOrder.setGoshShop(updateList.get(j).getGoshProduct()
					.getGoshShop());
			thisOrder.setOrderAmt(updateList.get(j).getProductQty());
			DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			Date orderdate = new Date();
			thisOrder.setOrderDate(orderdate);
			DBUtil.addToDB(thisOrder);

			GoshCartDB.delete(updateList.get(j));
		}

		String alert2 = "Cart reset!";

		// Set response content type
		response.setContentType("text/html");

		request.setAttribute("alert", alert);
		request.setAttribute("alert2", alert2);

		fullList = "";

		getServletContext().getRequestDispatcher("/GetMyOrder")
				.forward(request, response);
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