package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mm.da.DataAccess;
import mm.jsonModel.*;


public class ServerUtils {
	
	
	
	private ServerUtils(){}
	
	
	/** -- Session related methods: */
	
	public static String generateToken() {
        int length = 128;
        String candidateChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb =  new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }
	 
	
	/**Android related methods*/
	
	/**
	 * getJsonFromRequest
	 * @param request
	 * @param toJsonClass
	 * @return
	 * @throws IOException
	 */
	public static <T> T getJsonFromRequest(HttpServletRequest request,Class<T> toJsonClass) throws IOException {
		
	    Gson gson = new Gson();
	    return gson.fromJson(requestToJsonString(request),toJsonClass);
	}
	
	
	
	/**
	 * get json object from request stream
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static JsonObject  getJsonObjcetFromRequest(HttpServletRequest request) throws IOException {
		
	   
	    return  new JsonParser().parse(requestToJsonString(request)).getAsJsonObject();
	  	     
	    
	}
	
	
	/**
	 * parse request stream to json string
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String requestToJsonString(HttpServletRequest request) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

	    StringBuilder sb = new StringBuilder();
	    String s;
	    while ((s = br.readLine()) != null) {
	         sb.append(s).append("\n");
	    }
	    br.close();
	  //  System.out.println(sb.toString());
	    return sb.toString();
	}
	
	/**
	 * returns jsonUser as a response -- jsonUser contains status
	 * @param response
	 * @param jsonUser
	 * @throws IOException
	 */
	public static <T> void respondJsonObject(HttpServletResponse response,T jsonObjectClass) throws IOException {
		
		
		Gson gson =new Gson();
		
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(gson.toJson(jsonObjectClass));
		//System.out.println(gson.toJson(jsonUser).toString());
		out.flush();
		out.close();
		
	}
	
	/**
	 * check if the given token is valid for the given user
	 * @param userId
	 * @param token
	 * @param da
	 * @return
	 */
	public static boolean validateUserSession(int userId,String token,DataAccess da) {
		
		ArrayList<String> userSessions=null;
		//TODO
		//get method from DA
		//ArrayList<String> userSessions = da.getUserSessions(userId);
		
		for(String s:userSessions) {
			if(s.equals(token)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	//TODO -- add request validation methods for WEB
	/**Web related methods*/
}
