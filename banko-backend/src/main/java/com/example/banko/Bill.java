package com.example.banko;

import java.sql.*;

public class Bill {
    private int transaction_id;
    private String photo_url;

    public Bill(int transaction_id, String photo_url) {
        this.transaction_id = transaction_id;
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

        String query = "INSERT INTO bill (transaction_id, photo_url) VALUES (" + transaction_id + ", '" + photo_url
                + "')";

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
