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
import model.GoshCart;
import model.GoshUser;



/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetSummary")
public class GetSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String fullList = "";
		GoshCart myCart = (GoshCart) session.getAttribute("cart");
		GoshUser user = (GoshUser) session.getAttribute("User");
		double grandTotal = 0;
		double grandTax = 0;
		
		String qString = "SELECT g FROM GoshCart g where g.goshUser = ?1";
		TypedQuery<GoshCart> q = DBUtil.createQuery(qString, GoshCart.class);
		q.setParameter(1, user);
		List<GoshCart> cart = q.getResultList();

		for(int i=0;i<cart.size();i++)
        {
			long thisProductId = cart.get(i).getGoshProduct().getProductId();
			String thisProductName = cart.get(i).getGoshProduct().getProductName(); 
			int thisQty = cart.get(i).getProductQty();
			double thisPrice = cart.get(i).getGoshProduct().getProductPrice();
			double thisTax = thisQty*thisPrice*0.06;
			double thisSubtotal = thisQty*thisPrice+thisTax;
			
			fullList+="<li class=\"list-group-item\"><a href=\"GetProductDetail?id="
            		+thisProductId+"\">"+thisProductName+"</a><br>  "
            		+"<b>Price: $"+thisPrice+"</b><br>"
            		+"Qty: "+thisQty+"<br>"
            		+"Tax: $"+thisTax+"<br>"
            		+"Subtotal: $"+thisSubtotal+"<br>"
            		+"</li>";
            grandTotal+=thisSubtotal;
            grandTax+=thisTax;
			
        }
		
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				request.setAttribute("grandTotal", grandTotal);
				request.setAttribute("grandTax", grandTax);
				
				getServletContext().getRequestDispatcher("/summary.jsp")
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