import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GoshProduct;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetProductDetail")
public class GetProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long productid = Long.parseLong(request.getParameter("id"));
//		
		String qString = "SELECT g FROM GoshProduct g where g.productId = ?1";
		TypedQuery<GoshProduct> q = customTools.DBUtil.createQuery(qString, GoshProduct.class);
		q.setParameter(1, productid);
		GoshProduct p = new GoshProduct();
		p=q.getSingleResult();
		
		String thisName = p.getProductName();
		String thisPhoto = p.getProductPhoto();
		String thisDesc = p.getProductDesc();
		double thisQty = p.getProductQty();
		double thisPrice = p.getProductPrice();
		double thisShipping = p.getProductShip();
			
		String fullList = "";
		
			fullList="<li class=\"list-group-item\">Product Name: "+thisName+"</li>"
					+"<li class=\"list-group-item\">Photo: <img src=\""+thisPhoto+
					"\" style=\"width:120px;height:120px\"></li>"
					+"<li class=\"list-group-item\">Description: "+thisDesc+"</li>"
					+"<li class=\"list-group-item\">Price: $"+thisPrice+"</li>"
					+"<li class=\"list-group-item\">Shipping fee: $"+thisShipping+"</li>"
					+"<li class=\"list-group-item\">In Stock Qty: "+thisQty
					+"</li><br><br>";
		
		//Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				
				getServletContext().getRequestDispatcher("/ProductDetail.jsp")
						.forward(request, response);
				fullList = "";
				
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}