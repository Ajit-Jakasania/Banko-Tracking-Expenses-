package com.example.banko;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserGroup {

    private int user_id;
    private int group_id;


    public static ArrayList getUserGroup(String username) throws SQLException {
        Connection connection = BankoBackendServer.connection;
        int user_id = getUserId(username,connection);

        ArrayList<String> user = new ArrayList<>();
        String selectSql = "SELECT * FROM omjmf6vzmpqpgc0p.user WHERE username='" + username + "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
            user.add(Integer.toString(rs.getInt("user_id")));
            user.add(rs.getString("first_name"));
            user.add(rs.getString("last_name"));
            user.add(rs.getString("email"));
            user.add(rs.getString("phone_number"));
            user.add(Integer.toString(rs.getInt("address_id")));
            user.add(rs.getString("username"));
            user.add(rs.getString("hashed_password"));
            user.add(Integer.toString(rs.getInt("birth_month")));
            user.add(Integer.toString(rs.getInt("birth_day")));
            user.add(Integer.toString(rs.getInt("birth_year")));
            user.add(rs.getTimestamp("date_created").toString());
        }
        statement.close();
        return user;
    }

    private static int getUserId(String username, Connection connection) {
        int userid = -1;

        String query = "SELECT user_id FROM omjmf6vzmpqpgc0p.user WHERE username = '" + username + "'";
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

}
