package mm.da;

import java.sql.SQLException;
import java.util.ArrayList;

import mm.model.Meeting;
import mm.model.Mentee;
import mm.model.Mentor;
import mm.model.Pair;
import mm.model.Session;
import mm.model.User;
import mm.model.User.userType;

public interface DataInterface {
	public User login(String email) throws SQLException;
	public boolean deactivateUser(int id) throws SQLException;
	public ArrayList<User> getUsers(userType type) throws SQLException;
	public User getUser(int id) throws SQLException;
	public boolean addUser(User u) throws SQLException;
	public boolean editUser(User u) throws SQLException;
	public ArrayList<Pair> getAllPairs() throws SQLException;
	public boolean addPair(int mentorId, int menteeId) throws SQLException;
	public boolean disconnectPair(int pairId) throws SQLException;
	public Pair getPair(int pairId) throws SQLException;
	
	public ArrayList<Session> getUserSessions(int id);
	public ArrayList<Meeting> getUserMeetings(int id);
	public Mentor getMentorOfMentee(int menteeId);
	public ArrayList<Mentee> getMenteesOfMentor(int mentorId);
}
