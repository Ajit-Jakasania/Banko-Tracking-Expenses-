package com.example.banko;

import java.sql.*;

public class UserLogIn {

    private String username;
    private String hashed_password;

    public UserLogIn(String username, String hashed_password) {
        this.username = username;
        this.hashed_password = hashed_password;
    }

    public int loginUser() throws SQLException {

        String out[] = getStoredPassword(username);

        if (out[0] == null)
            return -1;

        String pw = out[0];
        int id = Integer.parseInt(out[1]);

        if (pw.equals(User.hashString(hashed_password)))
            return id;

        return -1;
    }

    private String[] getStoredPassword(String username) {

        Connection connection = BankoBackendServer.connection;

        String out[] = new String[2];
        out[0] = null;

        String query = "SELECT user_id, hashed_password FROM omjmf6vzmpqpgc0p.user WHERE username = '" + username + "'";

        try {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {

                out[0] = rs.getString("hashed_password");
                out[1] = Integer.toString(rs.getInt("user_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
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
