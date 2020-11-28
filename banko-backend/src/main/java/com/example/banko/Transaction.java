package com.example.banko;

import java.sql.*;

public class Transaction {

    private int group_id;
    private String transaction_content;
    private double amount;

    public Transaction(int group_id, String transaction_content, double amount) {
        this.group_id = group_id;
        this.transaction_content = transaction_content;
        this.amount = amount;
    }

    /**
     * Creates the transaction in the Transaction table and returns 1 if successful
     * and -1 if unsuccessful
     * 
     * @return
     * @throws SQLException
     */
    public int createTransaction() throws SQLException {
        Connection connection = BankoBackendServer.connection;

        String query = "INSERT INTO omjmf6vzmpqpgc0p.transaction (group_id, date_created, transaction_content, amount) VALUES("
                + group_id + ", NOW(), '" + transaction_content + "', " + amount + ")";

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

    /**
     * Updates the close date to now. Will be a button on the front end that will
     * call this for the specific transaction you're calling it for
     * 
     * returns 1 if success and -1 if fail
     * 
     * @param transaction_id
     * @return
     * @throws SQLException
     */
    public int closeTransaction(int transaction_id) throws SQLException {
        Connection connection = BankoBackendServer.connection;

        String query = "UPDATE omjmf6vzmpqpgc0p.transaction SET date_closed = NOW() WHERE transaction_id = "
                + transaction_id;
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