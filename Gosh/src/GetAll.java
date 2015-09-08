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

import customTools.DBUtil;
import model.GoshProduct;
import model.GoshShop;
import model.GoshUser;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetAll")
public class GetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAll() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("All").equals("shops")) {
			String List = "";

			String qString = "SELECT g FROM GoshShop g";
			TypedQuery<GoshShop> q = DBUtil
					.createQuery(qString, GoshShop.class);
			List<GoshShop> shops = q.getResultList();
			for (int i = 0; i < shops.size(); i++) {
				long shop_id = shops.get(i).getShopId();
				String photo = shops.get(i).getShopPhoto();
				String shop_name = shops.get(i).getShopName();
				String description = shops.get(i).getShopDesc();

				List += "<li class=\"list-group-item\"><img src=\"" + photo
						+ "\" style=\"width:120px;height:120px\">"
						+ "<a href=\"GetShopDetail?id=" + shop_id + "\">"
						+ shop_name + "</a><br>  " + description
						+ "</b><br></li>";
				// Set response content type
				
			}
			response.setContentType("text/html");
			request.setAttribute("List", List);
			getServletContext().getRequestDispatcher("/AllList.jsp")
					.forward(request, response);
			//List = null;
		} else {
			String List = "";
			String qString = "SELECT g FROM GoshProduct g";
			TypedQuery<GoshProduct> q = DBUtil.createQuery(qString,
					GoshProduct.class);
			List<GoshProduct> products = q.getResultList();
			for (int i = 0; i < products.size(); i++) {
				long product_id = products.get(i).getProductId();
				String photo = products.get(i).getProductPhoto();
				String product_name = products.get(i).getProductName();
				String description = products.get(i).getProductDesc();
				double price = products.get(i).getProductPrice();
				double qty = products.get(i).getProductQty();
				System.out.println(products.get(i));
				List += "<li class=\"list-group-item\"><form class=\"form-horizontal\" role=\"form\" method=\"get\" action=\"AddToCart\"><img src=\""+ photo+ "\" style=\"width:120px;height:120px\">"
						+"<input type=\"hidden\" name=\"productId\" value=\""+product_id+"\">"
						+ "<a href=\"GetProductDetail?id="+ product_id
						+ "\">" + product_name + "</a><br>  "
						+price
						+"<br>"
						+"Qty: "
						+"<input type=\"number\" name=\"quantity\" max=\""+qty+"\" min=\"0\">"
						+"<br>" +description
						+ "</b><br>"
						+"<input type=\"submit\" name=\"submit\" value=\"Add to cart\">"
						+ "</form></li>";
				// Set response content type
				
			}
			response.setContentType("text/html");
			request.setAttribute("List", List);
			getServletContext().getRequestDispatcher("/AllList.jsp")
					.forward(request, response);

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