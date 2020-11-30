package com.example.banko;

import java.sql.*;

public class UserLogIn {

    private String username;
    private String hashed_password;


    public UserLogIn(String username, String hashed_password) {
        this.username = username;
        this.hashed_password = hashed_password;
    }

    public boolean loginUser() throws SQLException {

        boolean login = false;
        Connection connection = getConnection();
        if (checkIfPasswordsMatch(username, hashed_password, connection)) {
            // logged in
            login = true;
        } else {
            //throw error
        }
        connection.close();
        return login;
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
        String hashed_pass = "";
        //hashed_pass = hash(password);
        String pw = getStoredPassword(username, connection);

        if (pw.equals(User.hashString(hashed_pass)))
            return true;

        return false;
    }

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
        return connection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }
}
