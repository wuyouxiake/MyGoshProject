import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GoshOrder;
import model.GoshProduct;
import model.GoshUser;
import customTools.DBUtil;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetMyOrder")
public class GetMyOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMyOrder() {
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
		GoshUser User = (GoshUser) session.getAttribute("User");
		long count = 0;
		String alert;
		double thisSubtotal;
		String fullList="<div class = \"container\"> <div style = \"border: 2px solid black\">";
		// get the number of orders.
		TypedQuery<Long> query = DBUtil
				.createQuery(
						"SELECT count( distinct g.orderId) FROM GoshOrder g WHERE g.goshUser = ?1",
						Long.class);
		query.setParameter(1, User);
		count = query.getSingleResult();
		System.out.println("COUNT " + count);
		if (count == 0) {
			alert = "You don't have any order.";
			// Set response content type
			// response.setContentType("text/html");
			request.setAttribute("alert", alert);
			alert = "";

		} else {
			// select unique order number of this user into a list.
			// List<Long> orderidList;
			// TypedQuery<Long> query2 =
			// DBUtil.createQuery("SELECT DISTINCT g.orderId FROM GoshOrder g WHERE g.goshUser = ?1",Long.class);
			// query2.setParameter(1, User);
			// orderidList=query2.getResultList();
			// Go over the order ID list, get Order ID, and print order.
			// for(int i=0;i<orderidList.size();i++){
			// long orderid=orderidList.get(i);
			TypedQuery<GoshOrder> tempQ = DBUtil.createQuery(
					"SELECT g FROM GoshOrder g WHERE g.goshUser = ?1",
					GoshOrder.class);
			tempQ.setParameter(1, User);
			// tempQ.setParameter(2, orderid);
			List<GoshOrder> thisOrder = tempQ.getResultList();
			
			thisSubtotal = 0;
			for (int j = 0; j < thisOrder.size(); j++) {
				GoshProduct thisProduct = thisOrder.get(j).getGoshProduct();
				String thisName = thisProduct.getProductName();
				// String thisPhoto = thisProduct.getProductPhoto();
				double thisPrice = thisProduct.getProductPrice();
				double thisQty = thisOrder.get(j).getOrderAmt();
				String thisTax = new DecimalFormat("#.##").format(thisPrice * thisQty * 0.06);
				String thisTotal = new DecimalFormat("#.##").format(thisPrice * thisQty * 1.06);
				String price = new DecimalFormat("#.##").format(thisPrice);
				String thisOrderDate = new SimpleDateFormat("MM/dd/yyyy").format(thisOrder.get(j).getOrderDate());
				fullList +=  "<br></br><div align=\"center\"> <div class = \"row\"><div class = \"col-sm-2\">"
						+ "<p><a href=\"GetProductDetail?id="
						+ thisProduct.getProductId() + "\" >" + thisName
						+ "</a></p></div><div class =\"col-sm-2\"><p>"
						+ price + "</p></div><div class =\"col-sm-2\"><p>"
						+ thisQty + "</p></div>" + "<div class =\"col-sm-2\"><p>"
						+ thisTax + "</p></div><div class =\"col-sm-2\"><p>"
						+ thisTotal + "</p></div><div class =\"col-sm-2\"><p>"
						+ thisOrderDate + "</p></div></div>"
						+"<p><a href=\"ReturnItem?productid="
						+ thisProduct.getProductId() + "&orderid="
						+ thisOrder.get(j).getOrderId()+"\" >Return"
						+ "</a></p>";
				/*
				 * fullList+= +thisName+"<br>" +"Price: "+thisPrice+"<br>"
				 * +"Qty: "+thisQty+"<br>" +"Tax: $"+thisTax+"<br>"
				 * +"Sub-total: $"+thisTotal+"<br>" +"</li>";
				 */
				// thisSubtotal+=thisTotal;

			}
			fullList+="</div></div>";

			// TypedQuery<Payment> tempQ3 =
			// em.createQuery("SELECT p FROM Payment p WHERE p.id = ?1",Payment.class);
			// tempQ3.setParameter(1, orderid);
			// Payment pay=tempQ3.getSingleResult();

			/*
			 * fullList+="<li class=\"list-group-item\">"
			 * +"<b>Total: $"+thisSubtotal+"</b><br>" +"<b>Order Date: </b><br>"
			 * +thisOrderDate+"<br>" +"<br>";
			 */
		}
		response.setContentType("text/html");

		request.setAttribute("fullList", fullList);
		request.setAttribute("count", count);

		getServletContext().getRequestDispatcher("/myOrder.jsp").include(
				request, response);
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