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

        if (checkIfPasswordsMatch(username, hashed_password)) {
            // logged in
            return true;
        } else {
            // throw error
        }
        return false;
    }

    private String getStoredPassword(String username) {
        Connection connection = BankoBackendServer.connection;

        String pw = null;
        String query = "SELECT hashed_password FROM omjmf6vzmpqpgc0p.user WHERE username = '" + username + "'";

        try {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(query);

            rs.next();

            pw = rs.getString("hashed_password");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pw;
    }

    private boolean checkIfPasswordsMatch(String username, String password) {

        // hashed_pass = hash(password);
        String pw = getStoredPassword(username);

        if (pw.equals(User.hashString(password)))
            return true;

        return false;
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
