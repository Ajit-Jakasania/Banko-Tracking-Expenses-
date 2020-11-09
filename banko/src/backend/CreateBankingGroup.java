import java.sql.*;
import java.util.*;

public class CreateBankingGroup {


    public static void main(String[] args) throws Exception {
        // Connection to MySql

        Connection connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
        
        //get username and banking group name from the front end using some REST api
        //clean data and check it for any exploits such as xss
        
      
        createGroup(name, username, connection); //from rest api
        
       

        // Close connection
        connection.close();

    }
    
    
    private boolean checkIfGroupExists(String name, Connection connection) {
    	String query = "SELECT * FROM database.group WHERE name = '" + name + "'";
        Statement statement;
         
        boolean exists = true;
		
        try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			   
			ResultSet rs = statement.executeQuery(query);
			if (!rs.next()) exists = false; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return exists; 
    }
    
   private int getUserId(String username, Connection connection) {
	   int userid = -1;
	   
	   String query = "SELECT user_id FROM database.user WHERE username = '" + username + "'";
       Statement statement;
        
       try {
    			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    			   
    			ResultSet rs = statement.executeQuery(query);
    			rs.next();
    			
    			userid = rs.getInt("user_id");
    		} catch (Exception e) {
    			e.printStackTrace();
    	}
       
       return userid;
   }
    
   private boolean createGroup(String name, String username, Connection connection) {
	   int userid = getUserId(username, connection);
       boolean failed = false;
	   
	   
	   if (!checkIfGroupExists(name, connection)) {
		   String query = "INSERT INTO group_list(group_name, user_id) VALUES ('" + name + "', " + userid + ")" ;
	       Statement statement;
	        
	       try {
	    			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    			   
	    			ResultSet rs = statement.executeQuery(query);
	    			rs.next();
	    			
	    			userid = rs.getInt("user_id");
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    			failed = true;
	    	}
	   }
       
	   return failed;
	   
   }
}