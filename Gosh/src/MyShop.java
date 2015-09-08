

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GoshUser;
import customTools.DBUtil;

/**
 * Servlet implementation class MyShop
 */
@WebServlet("/MyShop")
public class MyShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyShop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		model.GoshUser user = (GoshUser) session.getAttribute("User");
		model.GoshShop myShop = getShop(user.getUserEmail());
		if(myShop != null){
			request.setAttribute("shopName", myShop.getShopName());
			request.setAttribute("shopDesc", myShop.getShopDesc());
			request.setAttribute("shopPic", myShop.getShopPhoto());
			
			String products = printProducts(myShop.getGoshProducts());
			System.out.println("PRODUCTS " + myShop.getGoshProducts().size());
			request.setAttribute("productList", products);
			getServletContext().getRequestDispatcher("/MyShop.jsp").forward(request,response);
		}
		else{
			getServletContext().getRequestDispatcher("/createShop").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		model.GoshUser user = (GoshUser) session.getAttribute("User");
		model.GoshShop myShop = getShop(user.getUserEmail());
		model.GoshProduct product = new model.GoshProduct();
		product.setProductName(request.getParameter("name"));
		product.setProductDesc(request.getParameter("desc"));
		product.setProductPhoto(request.getParameter("photo"));
		product.setProductPrice(Double.parseDouble(request.getParameter("price")));
		product.setProductQty(Integer.parseInt(request.getParameter("qty")));
		product.setProductShip(Double.parseDouble(request.getParameter("ship")));
		product.setGoshShop(myShop);
		DBUtil.addToDB(product);
		doGet(request,response);
	}
	
	//only works for one shop right now
	protected model.GoshShop getShop(String email){
		String q = "select g from GoshShop g where g.goshUser.userEmail = :email";
		TypedQuery<model.GoshShop> tQuery = DBUtil.createQuery(q, model.GoshShop.class).setParameter("email", email);
		List<model.GoshShop> shops = tQuery.getResultList();
		System.out.println("SHOP " + shops.size());
		if(shops.size() > 0){
			return shops.get(0);
		}
		else return null;
	}
	
	protected String printProducts(List<model.GoshProduct> products){
		StringBuilder pList = new StringBuilder();
		for(model.GoshProduct product : products){
			pList.append("<div class = \"container\"> "
					+ "<div style = \"border: 2px solid black\">"
					+ "<div class = \"row\"><div class = \"col-sm-2\">"
					+ "<p><a href=\"GetProductDetail?id="
					+ product.getProductId()
					+ "\" >"
					+ product.getProductName()
					+ "</a></p></div><div class =\"col-sm-2\"><p>"
					+ product.getProductPrice()
					+ "</p></div>"
					+ "<div class=\"col-sm-2\">"
					+ "<p>"+ product.getProductQty() +"</p></div>"
					+ "<div class=\"col-sm-2\">"
					+"<p></div></div></div></div></div>");
		}
		return pList.toString();
	}
	

}
