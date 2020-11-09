package project;

import java.sql.*;
import java.util.*;

public class JoinGroup
{
	public static void main(String [] args) throws Exception
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
		
		//UserId is known because user needs to be logged in in order to join a group
		//Get the name of the group from the front end
		
		int userId; //get from session
		String groupName; //get from front end
		int groupId;
		
		
		//get the group_id from the group table. Maybe make this a function for simplicity
		String getQuery = "SELECT group_id FROM group WHERE group_name = " + groupName + ";";
		Statement statement;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(getQuery);
			
			rs.next();
			
			groupId = rs.getInt("group_id");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//get the time
		java.util.Date now = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
		
		//insert the user into the right group with the time joined
		String insertQuery = "INSERT INTO user_in_group (group_id, user_id, date_joined) VALUES "
								+ "(" + groupId + ", " + userId + ", " + sqlDate + ");";
								
		connection.close();
		
		
	}
}
