import java.sql.*;
import java.util.*;

public class UserLogIn {


    public static void main(String[] args) throws Exception {
        // Connection to MySql

        Connection connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
        
        //get username & password from the front end using some REST api
        //clean data and check it for any exploits such as xss
        
      
        
        if (checkIfPasswordsMatch(username, password, connection)) {
        	// logged in
        } else {
        	//throw error
        }
        
        
        
       

        // Close connection
        connection.close();

    }
    
    private String getStoredPassword(String username, Connection connection) {
          
    	String pw = null;  
    	String query = "SELECT password FROM database.user WHERE username = '" + username + "'";
        Statement statement;
          
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			   
			ResultSet rs = statement.executeQuery(query);
			
			rs.next();
		          
			pw = rs.getString("password");

		} catch (Exception e) {
			e.printStackTrace();
		}
          
       
          return pw;
    }
    
    private boolean checkIfPasswordsMatch(String username, String password, Connection connection) {
    	//hash the input password
    	String pw = getStoredPassword(username, connection);
    	
    	if (pw == hash(password)) return true;
    	return false; 
    }
}