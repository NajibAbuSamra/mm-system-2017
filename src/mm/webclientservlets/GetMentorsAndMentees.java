package mm.webclientservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.da.DataAccess;
import mm.model.User;

/**
 * Servlet implementation class GetMentorsAndMentees
 * return all mentees and mentors;
 */
@WebServlet("/GetMentorsAndMentees")
public class GetMentorsAndMentees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMentorsAndMentees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String NextPage = request.getParameter("jsp");
		ArrayList<User> ArrMentors = new ArrayList<User>();
		DataAccess da = new DataAccess();
	//	ArrMentors=getAllUsers();
		 /*try {
		 ArrMentors = da.getUsers(userType.MENTOR);
		 } catch (SQLException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }*/
		request.setAttribute("Mentors", ArrMentors);
		System.out.println("MENTORS: " + ArrMentors);
		PrintWriter writer = response.getWriter();
		writer.println(ArrMentors);
		
		// Mentees

		ArrayList<User> ArrMentees = new ArrayList<User>();
	//	ArrMentees=getAllUsers();
//		 try {
//		 ArrMentees = da.getMenteesWhithOutMentor();
//		 } catch (SQLException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
		
		request.setAttribute("Mentees", ArrMentees);
		System.out.println("Mentees: " + ArrMentees);
		RequestDispatcher req = request.getRequestDispatcher(NextPage);
		req.forward(request, response);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	
	
	
	}

}