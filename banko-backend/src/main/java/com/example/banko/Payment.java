package com.example.banko;

import java.sql.*;
import com.example.BankoBackendServer;

public class Payment {
    private int user_id;
    private int transaction_id;
    private int payment_complete;

    public Payment(int user_id, int transaction_id) {
        this.user_id = user_id;
        this.transaction_id = transaction_id;
        payment_complete = 0;
    }

    /**
     * Makes a pending payment for a user of user_id for the amount of the
     * transaction specified by the transaction id
     * 
     * @return
     */
    public int initializePayment() throws SQLException {

        Connection connection = BankoBackendServer.connection;

        String query = "INSERT INTO payment (user_id, transaction_id, payment_complete) VALUES (" + user_id + ", "
                + transaction_id + ", " + payment_complete + ")";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            statement.executeUpdate(query);
            return 1; // successful insert

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * A feature to indicate that a user has made a payment.
     * 
     * @return
     */
    public boolean makePayment() throws SQLException {
        Connection connection = BankoBackendServer.connection;

        String query = "UPDATE payment SET payment_complete = 1 WHERE user_id = " + user_id;

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            statement.executeUpdate(query);
            return true; // successful insert

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
