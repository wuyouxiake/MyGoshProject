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
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String target = request.getParameter("target");
		String choice = request.getParameter("choice");
		String List = null;
		if(choice.equals("shop")){
			String qString1 = "SELECT count(g) FROM GoshShop g where g.shopName like ?1";
			TypedQuery<Long> q1 = DBUtil.createQuery(qString1, Long.class).setParameter(1, "%" + target + "%");
			if(q1.getSingleResult()==0){
				//Set response content type
				response.setContentType("text/html");
				String alert = "No matching Shop!";
				request.setAttribute("alert", alert);
				getServletContext().getRequestDispatcher("/error.jsp")
						.forward(request, response);
				alert="";
			}else{
				String qString= "SELECT g FROM GoshShop g where g.shopName like ?1";
				TypedQuery<GoshShop> q = DBUtil.createQuery(qString, GoshShop.class).setParameter(1, "%" + target + "%");
					List<GoshShop> shops=q.getResultList();
					for(int i=0;i<shops.size();i++){
						long shop_id = shops.get(i).getShopId();
						String photo = shops.get(i).getShopPhoto();
						String shop_name = shops.get(i).getShopName();
						String description = shops.get(i).getShopDesc();
						
						List += "<li class=\"list-group-item\"><img src=\""+ photo+ "\" style=\"width:120px;height:120px\">"
								+ "<a href=\"GetShopDetail?id="+ shop_id
								+ "\">" + shop_name + "</a><br>  "
								+ description
								+ "</b><br></li>";
						//Set response content type
						
						}
					response.setContentType("text/html");
					request.setAttribute("List", List);
					getServletContext().getRequestDispatcher("/SearchList.jsp")
							.forward(request, response);
					}
			}else if(choice.equals("product")){
				String qString1 = "SELECT count(g) FROM GoshProduct g where g.productName like ?1";
				TypedQuery<Long> q1 = DBUtil.createQuery(qString1, Long.class).setParameter(1, "%" + target + "%");
				if(q1.getSingleResult()==0){
					//Set response content type
					response.setContentType("text/html");
					String alert = "No matching Product!";
					request.setAttribute("alert", alert);
					getServletContext().getRequestDispatcher("/error.jsp")
							.forward(request, response);
					alert="";
				}else{
					String qString = "SELECT g FROM GoshProduct g where g.productName like ?1";
					TypedQuery<GoshProduct> q = DBUtil.createQuery(qString, GoshProduct.class).setParameter(1, "%" + target + "%");
						List<GoshProduct> products=q.getResultList();
						for(int i=0;i<products.size();i++){
							long product_id = products.get(i).getProductId();
							String photo = products.get(i).getProductPhoto();
							String product_name = products.get(i).getProductName();
							String description = products.get(i).getProductDesc();
							double price = products.get(i).getProductPrice();
							double qty = products.get(i).getProductQty();
							
							List += "<li class=\"list-group-item\"><img src=\""+ photo+ "\" style=\"width:120px;height:120px\">"
									+ "<a href=\"GetProductDetail?id="+ product_id
									+ "\">" + product_name + "</a><br>  "
									+price
									+"<br>/"
									+qty
									+" In-stock<br>/"
									+ description
									+ "</b><br></li>";
							//Set response content type
							
						
							}
						response.setContentType("text/html");
						request.setAttribute("List", List);
						getServletContext().getRequestDispatcher("/SearchList.jsp")
								.forward(request, response);
						}
				}
				List=null;
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