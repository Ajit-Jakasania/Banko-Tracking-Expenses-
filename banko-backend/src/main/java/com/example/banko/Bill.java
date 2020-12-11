package com.example.banko;

import java.sql.*;
import com.example.BankoBackendServer;

public class Bill {
    public String transaction_content;
    public double amount;
    public String group_name;
    private int transaction_id;
    private String photo_url;
    private int group_id;

    public Bill(String group_name, String photo_url) {
        this.group_name = group_name;
        this.photo_url = photo_url;
    }

    /**
     * Adds a bill which is just a photo linked to a certain transaction returns 1
     * for succes, 0 for error
     * 
     * @return
     */
    public int createBill() throws SQLException {
        Connection connection = BankoBackendServer.connection;
        group_id = getGroupId(group_name, connection);
        transaction_id = getTransactionId(group_id, connection);

        String query = "INSERT INTO omjmf6vzmpqpgc0p.bill (transaction_id, photo_url) VALUES (" + transaction_id + ", '"
                + photo_url + "')";

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

    private int getGroupId(String group_name, Connection connection) {
        int group_id = -1;

        String query = "SELECT group_id FROM omjmf6vzmpqpgc0p.group_list WHERE group_name = '" + group_name + "'";
        Statement statement;

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);
            rs.next();

            group_id = rs.getInt("group_id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return group_id;
    }

    private int getTransactionId(int group_id, Connection connection) {
        int transaction_id = -1;

        String query = "SELECT transaction_id FROM omjmf6vzmpqpgc0p.transaction WHERE group_id =" + group_id;

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);
            rs.last();

            transaction_id = rs.getInt("transaction_id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transaction_id;
    }
}
