package com.example.banko;

import java.sql.*;

public class Message {
    private String content;
    private int user_id;
    private int group_id;

    public Message(String content, int user_id, int group_id) {
        this.content = content;
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public int sendGroupMessage() throws SQLException {

        Connection connection = BankoBackendServer.connection;
        // first add message content to content table

        int messageContentID = addMessageContent(content, connection);

        if (messageContentID == -1)
            return -1;

        if (addMessage(messageContentID, group_id, user_id, connection) == 1)
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

            query = "SELECT LAST_INSERTED_ID()";

            ResultSet rs = statement.executeQuery(query);
            // gets last inserted key, aka the content id we just inserted into the table
            if (rs.next())
                return Integer.parseInt(rs.getString("message_content_id")); // returns the message content id

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int addMessage(int messageContentID, int group_id, int user_id, Connection connection) {
        String query;

        query = "INSERT INTO omjmf6vzmpqpgc0p.message (message_content_id, group_id, user_id) VALUES ("
                + messageContentID + ", " + group_id + ", " + user_id + ", NOW())";

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
