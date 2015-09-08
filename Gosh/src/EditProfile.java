

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import javax.persistence.TypedQuery;
/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private model.GoshUser user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		user = (model.GoshUser) session.getAttribute("User");
		String header = "Edit Profile";
		request.setAttribute("headName",header);
		request.setAttribute("action","EditProfile");
		request.setAttribute("name",user.getFullname());
		request.setAttribute("email",user.getUserEmail());
		request.setAttribute("username", user.getUsername());
		request.setAttribute("password",user.getUserPassword());
		getServletContext().getRequestDispatcher("/SignUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String initEmail = user.getUserEmail();
			if(!request.getParameter("name").equals(""))
			user.setFullname(request.getParameter("name"));
			if(!request.getParameter("email").equals(""))
			user.setUserEmail(request.getParameter("email"));
			if(!request.getParameter("pwd").equals(""))
			user.setUserPassword(request.getParameter("pwd"));
			String q = "UPDATE GoshUser g SET g.fullname =:name, u.userEmail=:email, u.userPassword=:pass" 
					+ " WHERE u.userEmail = :initEmail";
			TypedQuery<model.GoshUser> query = DBUtil.createQuery(q, model.GoshUser.class);
			query.setParameter("initEmail", initEmail).setParameter("name", user.getFullname())
			.setParameter("email",user.getUserEmail()).setParameter("pass",user.getUserPassword());
			DBUtil.updateDB(query);
			
			request.setAttribute("alertMessage", "<div class=\"container\"><div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"+
				    "<strong>Success!</strong> You have edited your profile.</div></div>");
			HttpSession session = request.getSession();
			session.setAttribute("User", user);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("alertMessage", "<div class=\"container\"><div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"+
					    "<strong>Success!</strong> You have edited your Profile.</div></div>");
		doGet(request,response);
		//getServletContext().getRequestDispatcher("/SignUp.jsp").forward(request,
		//		response);
	}

	
}
