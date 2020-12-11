package com.example.banko;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import com.example.BankoBackendServer;

public class UserGroupMessage {

    public static ArrayList<HashMap<String, String>> getUserGroupMessage(String group_name) throws SQLException {
        Connection connection = BankoBackendServer.connection;
        ArrayList<HashMap<String, String>> listGroupMessages = new ArrayList<HashMap<String, String>>();

        // nested query for getting group id
        String getGroupID = "(SELECT group_id FROM omjmf6vzmpqpgc0p.group_list WHERE group_name = '" + group_name
                + "')";

        String selectSql = "SELECT message_content_id, content, group_id, user_id, username, date_sent FROM omjmf6vzmpqpgc0p.message "
                + "JOIN omjmf6vzmpqpgc0p.message_content USING (message_content_id) JOIN omjmf6vzmpqpgc0p.user USING (user_id) WHERE (group_id = "
                + getGroupID + ") ORDER BY date_sent ASC";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
            HashMap<String, String> userGroupMessageJSON = new HashMap<String, String>();
            userGroupMessageJSON.put("message_content_id", Integer.toString(rs.getInt("message_content_id")));
            userGroupMessageJSON.put("content", rs.getString("content"));
            userGroupMessageJSON.put("group_id", Integer.toString(rs.getInt("group_id")));
            userGroupMessageJSON.put("user_id", Integer.toString(rs.getInt("user_id")));
            userGroupMessageJSON.put("username", rs.getString("username"));
            userGroupMessageJSON.put("date_sent", (rs.getString("date_sent")));
            listGroupMessages.add(userGroupMessageJSON);
        }
        statement.close();
        return listGroupMessages;
    }

}
