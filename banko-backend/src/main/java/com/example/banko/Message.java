package com.example.banko;

import java.sql.*;
import com.example.BankoBackendServer;

public class Message {
    public String content;
    public int user_id;
    public String group_name;

    public Message() {
        content = null;
        user_id = -1;
        group_name = null;
    }

    public Message(String content, int user_id, String group_name) {
        this.content = content;
        this.user_id = user_id;
        this.group_name = group_name;
    }

    public int sendGroupMessage() throws SQLException {

        Connection connection = BankoBackendServer.connection;
        // first add message content to content table

        int messageContentID = addMessageContent(content, connection);

        if (messageContentID == -1)
            return -1;

        if (addMessage(messageContentID, group_name, user_id, connection) == 1)
            return 1;
        else
            return -1;

    }

    /**
     * Adds message to message content and returns its id. If error, returns -1
     * 
     * @param content
     * @param connection
     * @return
     */
    private static int addMessageContent(String content, Connection connection) {
        String query;

        query = "INSERT INTO omjmf6vzmpqpgc0p.message_content (content, date_sent) VALUES ('" + content + "', NOW())";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            statement.executeUpdate(query);

            query = "SELECT message_content_id FROM omjmf6vzmpqpgc0p.message_content ORDER BY message_content_id DESC LIMIT 1";

            ResultSet rs = statement.executeQuery(query);
            // gets last inserted key, aka the content id we just inserted into the table
            if (rs.next())
                return rs.getInt("message_content_id"); // returns the message content id

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int addMessage(int messageContentID, String group_name, int user_id, Connection connection) {
        String query;
        String groupid = "(SELECT group_id FROM omjmf6vzmpqpgc0p.group_list WHERE group_name = '" + group_name + "')";

        query = "INSERT INTO omjmf6vzmpqpgc0p.message (message_content_id, group_id, user_id) VALUES ("
                + messageContentID + ", " + groupid + ", " + user_id + ")";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            statement.executeUpdate(query);

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
