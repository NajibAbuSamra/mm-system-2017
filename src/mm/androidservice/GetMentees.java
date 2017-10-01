package mm.androidservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import mm.constants.Constants;
import mm.da.DataAccess;
import mm.da.DataInterface;
import mm.jsonModel.JsonUser;
import mm.jsonModel.JsonUsers;
import mm.model.Mentee;
import mm.model.User;
import util.ServerUtils;

/**
 * Servlet implementation class GetMentees
 */
@WebServlet("/GetMentees")
public class GetMentees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMentees() {
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
		JsonObject myJson = ServerUtils.getJsonObjectFromRequest(request);
		
		int id =(myJson.get("id").isJsonNull() ? 0 : myJson.get("id").getAsInt());
		String token = myJson.get("token").getAsString();

		DataInterface da = new DataAccess();
		JsonUsers jsonUsers=null;
		List<Mentee> mentees=null;
		
		if(ServerUtils.validateUserSession(id,token,da)) {
		
		
		
		try {
			mentees=da.getMenteesOfMentor(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (mentees == null) {

			//jsonUsers = new JsonUser(mentees, Constants.STATUS_MISSINGPARA, Constants.USERNOTFOUND, null);
		} else {
			
			//jsonUsers =new JsonUser(mentees,Constants.STATUS_SUCCESS,Constants.SUCCESS,token);
			
			}
		}
		else {
			jsonUsers= new JsonUsers(null, Constants.STATUS_MISSINGPARA, Constants.INVALID_SESSION_TOKEN, null);
		}

		
		ServerUtils.respondJsonObject(response,jsonUsers);
		
		
	
	}

}