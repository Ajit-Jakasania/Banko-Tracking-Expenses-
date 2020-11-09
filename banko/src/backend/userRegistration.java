import java.sql.*;
import java.util.*;

public class userRegistration {
	
		
		//Getting the user data from api 
		//Get method to fetch data from the front end 
		
		//Clean the data
		//check for xss exploits
	
		//Check the data for requirements from the user table
	
		//Check the country_id and city_id that match from the country and city tables in MySQL
		
		//Hashed password which is length 128
		
		//Store the data in the user table in MySQL 
		//Check if the data was stored properly
		
		//Return value to front-end, fail=0, pass=1
		
		public static void main(String[] args) throws Exception {
			
			//Call above functions in series
			String phoneNumber = "";
			String username = "";
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
			
			//Getting the user data from api 
			//Get method to fetch data from the front end 
			//clean data and check it for any exploits such as xss
			
			//Check the data for requirements from the user table
			
			//Phone number is 11 digits
			boolean checkPhoneNumber = false;
			boolean uniUsername = false;
			checkPhoneNumber = phoneNumberDigits(phoneNumber);
			
			//Check if the username is unique
			uniUsername = uniqueUsername(username, connection); 
			
			
			if(checkPhoneNumber)
			{
				if(uniUsername)
				{
					//Check the country_id and city_id that match from the country and city tables in MySQL
					
					//Hashed password function which returns a string of length 128
					
					//Store the data in the user table in MySQL 
					//Check if the data was stored properly
				}
				else
				{
					//Return to front-end, username is not unique
				}
			}
			else
			{
				//Return to front-end, phoneNumber is not 11 digits
			}
			
			
			//Return value to front-end, fail=0, pass=1
			
		}
		
		private static boolean uniqueUsername(String username, Connection connection)
		{
			String usernameInDatabase = "";
			String query = "SELECT username FROM omjmf6vzmpqpgc0p.user";
			try {
				
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = statement.executeQuery(query);
				while(rs.next())
				{
					usernameInDatabase = rs.getString("username");
					if(usernameInDatabase.equals(username))
					{
						//Username is not unique
						return false;
					}
				}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}

			//Username is unique
			return true;
		}
		
		private static boolean phoneNumberDigits(String phoneNumber)
		{
			if(phoneNumber.length()==11)
			{
				return true;
			}
			return false;
		}
}
