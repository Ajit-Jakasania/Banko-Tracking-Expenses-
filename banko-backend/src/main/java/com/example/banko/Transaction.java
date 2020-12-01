package com.example.banko;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {

    private int group_id;
    private String transaction_content;
    private double amount;

    private int transaction_id;

    public Transaction(int group_id, String transaction_content, double amount) {
        this.group_id = group_id;
        this.transaction_content = transaction_content;
        this.amount = amount;
    }

    public Transaction(int transaction_id) {
        this.transaction_id = transaction_id;
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

    public static ArrayList<HashMap<String,String>> getUserTransactions(int user_id)
    {
        Connection connection = BankoBackendServer.connection;
        ArrayList<HashMap<String,String>> listAllGroupTransactions = new ArrayList<HashMap<String,String>>();
        ArrayList<HashMap<String,String>> listTransactions = new ArrayList<HashMap<String,String>>();

        String selectSql = "SELECT group_id FROM omjmf6vzmpqpgc0p.user_in_group WHERE user_id =" + user_id ;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(selectSql);
            while (rs.next()) {
                HashMap<String, String> userTransactionJSON = new HashMap<String, String>();
                userTransactionJSON.put("group_id",Integer.toString(rs.getInt("group_id")));
                listTransactions.add(userTransactionJSON);

            }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
            for(HashMap<String, String> map: listTransactions){
                for(String s: map.keySet()){
                    String group_id = map.get(s);
                    int tempGroup_id = Integer.parseInt(group_id);
                    String query = "SELECT * FROM omjmf6vzmpqpgc0p.transaction WHERE group_id =" + tempGroup_id ;
                    try {
                        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);

                        ResultSet rs = statement.executeQuery(query);
                        while (rs.next()) {
                            HashMap<String, String> userAllTransactionJSON = new HashMap<String, String>();
                            userAllTransactionJSON.put("transaction_id",Integer.toString(rs.getInt("transaction_id")));
                            userAllTransactionJSON.put("group_id",Integer.toString(rs.getInt("group_id")));
                            userAllTransactionJSON.put("date_created", (rs.getString("date_created")));
                            userAllTransactionJSON.put("date_closed", (rs.getString("date_closed")));
                            userAllTransactionJSON.put("transaction_content", (rs.getString("transaction_content")));
                            userAllTransactionJSON.put("amount",Double.toString(rs.getDouble("amount")));
                            listAllGroupTransactions.add(userAllTransactionJSON);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }

        return listAllGroupTransactions;
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
    public int closeTransaction() throws SQLException {
        Connection connection = BankoBackendServer.connection;

        String query = "UPDATE omjmf6vzmpqpgc0p.transaction SET date_closed = NOW() WHERE transaction_id = "
                + this.transaction_id;
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