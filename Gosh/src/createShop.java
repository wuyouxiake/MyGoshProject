

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.GoshUser;

/**
 * Servlet implementation class createShop
 */
@WebServlet("/createShop")
public class createShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createShop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/createShop.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		model.GoshUser user = (GoshUser) session.getAttribute("User");
		model.GoshShop shop = new model.GoshShop();
		shop.setGoshUser(user);
		shop.setShopName(request.getParameter("shopName"));
		shop.setShopDesc(request.getParameter("shopDesc"));
		shop.setShopPhoto(request.getParameter("shopPic"));
		DBUtil.addToDB(shop);
		getServletContext().getRequestDispatcher("/MyShop").forward(request, response);
	}

}
