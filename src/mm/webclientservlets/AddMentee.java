package mm.webclientservlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GeneratePass;
import mm.da.DataAccess;
import mm.model.Mentee;
import mm.model.Mentor;
import mm.model.User;
import mm.model.User.userType;

/**
 * Servlet implementation class AddMentee
 */
@WebServlet("/AddMentee")
public class AddMentee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMentee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage=request.getParameter("jsp");
		GeneratePass genPass=new GeneratePass();
		String uFirstName = request.getParameter("uFirstName");
		String uLastName = request.getParameter("uLastName");
		String uPhoneNumber = request.getParameter("uPhoneNumber");
		String uEmail = request.getParameter("uEmail");
		String uGender = request.getParameter("uGender");
		String uAddress = request.getParameter("uAddress");
		String uGraduationStatus = request.getParameter("uGraduationStatus");
		String uCourseOfStudy = request.getParameter("uCourseOfStudy");
		String uAcademicInstitution = request.getParameter("uAcademicInstitution");
		String uRemSemesters = request.getParameter("uRemSemesters");
		String uAverage = request.getParameter("uAverage");
		String uNotes = request.getParameter("uNotes");
		String academicDicipline =request.getParameter("uAcademicDicipline");
		String academicDicipline2=request.getParameter("uAcademicDicipline2");
		String isGraduate=request.getParameter("uIsGraduate");
		String resume=request.getParameter("uResume");
		String gradeSheet=request.getParameter("uGradeSheet");
		
		float remSemesters=Float.valueOf(uRemSemesters);
		float avg=Float.valueOf(uAverage);
		
		boolean isGradute=Boolean.parseBoolean(isGraduate);
		String uPass= genPass.getSaltString();	 
		User newMentee=new Mentee(uFirstName,uLastName,uEmail,uPhoneNumber,uPass,uGender,uAddress,uNotes,true,userType.MENTEE,remSemesters,uGraduationStatus,uAcademicInstitution, avg,academicDicipline,academicDicipline2,isGradute,resume,gradeSheet );
		
		
		DataAccess da = new DataAccess();
	    boolean res=false;
	
//		try {
//			res = da.addUser(newMentee)
//		} catch (SQLException e) {
////			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(res){
			request.setAttribute("Status", 200);
		}
		if(!res)
		request.setAttribute("Status", 400);
		
		RequestDispatcher req = request.getRequestDispatcher(nextPage);
		req.forward(request, response);
	}
	}