package mm.androidservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import mm.constants.Constants;
import mm.da.DataAccess;
import mm.da.DataInterface;
import mm.model.Meeting;
import util.ServerUtils;

/**
 * Servlet implementation class ApproveMeeting
 */
@WebServlet("/ApproveMeeting")
public class ApproveMeeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveMeeting() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AndroidIOManager iom = new AndroidIOManager(request,response);
		JsonObject jsonMeetingToApprove = iom.getJsonRequest();
		//int id,String token,String meeting_id,boolean action
		int id = jsonMeetingToApprove.get("id").getAsInt();
		String token = jsonMeetingToApprove.get("token").getAsString();
		String meetingId = jsonMeetingToApprove.get("meeting_id").getAsString();
		Boolean action = jsonMeetingToApprove.get("action").getAsBoolean();
		
		if(ServerUtils.validateUserSession(id, token, iom.getDataAccess())){
			if(meetingId!=null && action!=null) {
				try {
					if(iom.getDataAccess().approveMeeting(Integer.parseInt(meetingId), action)){
						
						iom.setResponseMessage(new RESPONSE_STATUS(RESPONSE_STATUS.SUCCESS));
						
				

						
					}else {
						iom.setResponseMessage(new RESPONSE_STATUS(RESPONSE_STATUS.DATABASE_ERROR));
							
					}
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
							
			}else {
				iom.setResponseMessage(new RESPONSE_STATUS(RESPONSE_STATUS.PARAM_FAILED));
				
				
			}
		
			
		}else {
			iom.setResponseMessage(new RESPONSE_STATUS(RESPONSE_STATUS.INVALID_SESSION));

			
		}
		
		iom.SendJsonResponse();
		
	}

}