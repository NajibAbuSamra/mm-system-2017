package mm.androidservice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import mm.constants.Constants;
import mm.da.DataAccess;
import mm.jsonModel.JsonUser;
import mm.model.User;
import mm.webclientservlets.GetMentorById;
import util.ServerUtils;

/**
 * Servlet implementation class GetMentor
 */
@WebServlet("/GetMentor")
public class GetMentor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMentor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		JsonObject myJson = ServerUtils.getJsonObjcetFromRequest(request);
		
		int id = (int) (myJson.get("id").isJsonNull() ? "" : myJson.get("id").getAsInt());
		String token = myJson.get("token").getAsString();
		
		User mentee = null,mentor=null;
		JsonUser jsonUser=null;
		int MentorId,MenteeId;
		
		DataAccess da = new DataAccess();
		if(ServerUtils.validateUserSession(id,token,da)) {
			

			//user=da.getUser(myUser.id);
		
		if (mentee == null) {

			jsonUser = new JsonUser(mentee, Constants.STATUS_MISSINGPARA, Constants.USERNOTFOUND, null);
		} else {
					//mentor=da.getUser(mentee.mentorID);
			if (mentor == null) {

				jsonUser = new JsonUser(mentor, Constants.STATUS_MISSINGPARA, Constants.USERNOTFOUND, null);
			} else {
				
				jsonUser=new JsonUser(mentor,Constants.STATUS_SUCCESS,Constants.SUCCESS,token);
				
				}
			
			}
		}
		else {
			jsonUser = new JsonUser(null, Constants.STATUS_MISSINGPARA, Constants.INVALID_SESSION_TOKEN, null);
		}

		
		ServerUtils.respondJsonObject(response,jsonUser);
		
	}

}
