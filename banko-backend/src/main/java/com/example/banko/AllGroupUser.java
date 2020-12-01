package com.example.banko;

import java.sql.*;
import java.util.*;


public class AllGroupUser {

    public static ArrayList<HashMap<String,String>> getGroupUser(int group_id) throws SQLException {
        Connection connection = BankoBackendServer.connection;
        ArrayList<HashMap<String,String>> listGroupUsers = new ArrayList<HashMap<String,String>>();

        String selectSql = "SELECT username FROM omjmf6vzmpqpgc0p.user JOIN omjmf6vzmpqpgc0p.user_in_group USING (user_id) WHERE group_id =" + group_id ;
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
            HashMap<String, String> allGroupUserJSON = new HashMap<String, String>();
            allGroupUserJSON.put("username",rs.getString("username"));
            listGroupUsers.add(allGroupUserJSON);
        }
        statement.close();
        return listGroupUsers;
    }
}
