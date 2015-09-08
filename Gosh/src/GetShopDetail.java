import java.io.IOException;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GoshProduct;
import model.GoshShop;
import model.GoshUser;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetShopDetail")
public class GetShopDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetShopDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long shopid = Long.parseLong(request.getParameter("id"));
//		
		String qString = "SELECT g FROM GoshShop g where g.shopId = ?1";
		TypedQuery<GoshShop> q = customTools.DBUtil.createQuery(qString, GoshShop.class);
		q.setParameter(1, shopid);
		GoshShop s = new GoshShop();
		s=q.getSingleResult();
		
		String thisName = s.getShopName();
		String thisPhoto = s.getShopPhoto();
		String thisDesc = s.getShopDesc();
		GoshUser user = new GoshUser();
		user = s.getGoshUser();
		String thisUserName = user.getFullname();
		String thisUserEmail = user.getUserEmail();
		
			
		String fullList = "";
		
			fullList="<li class=\"list-group-item\">Shop Name: "+thisName+"</li>"
					+"<li class=\"list-group-item\">Photo: <img src=\""+thisPhoto+
					"\" style=\"width:120px;height:120px\"></li>"
					+"<li class=\"list-group-item\">Description: "+thisDesc+"</li>"
					+"<li class=\"list-group-item\">Shop Owner: $"+thisUserName+"</li>"
					+"<li class=\"list-group-item\">Email: $"+thisUserEmail
					+"</li><br><br>";
		
		//Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);
				
				getServletContext().getRequestDispatcher("/ShopDetail.jsp")
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