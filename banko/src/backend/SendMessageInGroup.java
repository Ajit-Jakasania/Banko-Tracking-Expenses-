import java.sql.*;
import java.util.*;

public class SendMessageInGroup {


    public static void main(String[] args) throws Exception {
        // Connection to MySql

        Connection connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
        
        //get userid, group id, and message and timestamp from the front end
        //clean the data
        //check if user is in group
        
        //add message to message content and retrieve the key
        
        //add message content key + everything else into message
      
        
       

        // Close connection
        connection.close();

    }
    
 
}