package com.example.banko;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import com.example.BankoBackendServer;

public class UserGroup {

    private int user_id;
    private int group_id;

    public static ArrayList<HashMap<String, String>> getUserGroup(int user_id) throws SQLException {
        Connection connection = BankoBackendServer.connection;
        ArrayList<HashMap<String, String>> listGroups = new ArrayList<HashMap<String, String>>();

        String selectSql = "SELECT group_id, group_name, date_created, date_joined FROM omjmf6vzmpqpgc0p.user_in_group JOIN omjmf6vzmpqpgc0p.group_list USING (group_id) WHERE user_id ="
                + user_id;
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
            HashMap<String, String> userGroupJSON = new HashMap<String, String>();
            userGroupJSON.put("group_id", Integer.toString(rs.getInt("group_id")));
            userGroupJSON.put("group_name", rs.getString("group_name"));
            userGroupJSON.put("date_created", (rs.getString("date_created")));
            userGroupJSON.put("date_joined", (rs.getString("date_joined")));
            listGroups.add(userGroupJSON);
        }
        statement.close();
        return listGroups;
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
