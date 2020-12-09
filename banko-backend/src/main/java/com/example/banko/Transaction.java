package com.example.banko;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {

    public int group_id;
    public String transaction_content;
    public double amount;
    public String group_name;
    public String bill;
    public int transaction_id;

    public Transaction() {
        group_id = -1;
        transaction_content = null;
        amount = -1;
        transaction_id = -1;
        bill = null;
    }

    public Transaction(String group_name, String transaction_content, double amount) {
        this.group_name = group_name;
        this.transaction_content = transaction_content;
        this.amount = amount;
        bill = null;

    }

    public Transaction(int group_id, String transaction_content, double amount) {
        this.group_id = group_id;
        this.transaction_content = transaction_content;
        this.amount = amount;
        bill = null;

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
        group_id = getGroupId(group_name, connection);
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

    public static ArrayList<HashMap<String, String>> getUserTransactions(int user_id) {
        Connection connection = BankoBackendServer.connection;
        ArrayList<HashMap<String, String>> listAllGroupTransactions = new ArrayList<HashMap<String, String>>();
        ArrayList<HashMap<String, String>> listTransactions = new ArrayList<HashMap<String, String>>();

        String selectSql = "SELECT group_id FROM omjmf6vzmpqpgc0p.user_in_group WHERE user_id =" + user_id;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(selectSql);
            while (rs.next()) {
                HashMap<String, String> userTransactionJSON = new HashMap<String, String>();
                userTransactionJSON.put("group_id", Integer.toString(rs.getInt("group_id")));
                listTransactions.add(userTransactionJSON);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (HashMap<String, String> map : listTransactions) {
            for (String s : map.keySet()) {
                String group_id = map.get(s);
                int tempGroup_id = Integer.parseInt(group_id);
                String query = "SELECT transaction.transaction_id, group_id, date_created, date_closed, transaction_content,"
                        + " amount, photo_url  FROM omjmf6vzmpqpgc0p.transaction LEFT JOIN omjmf6vzmpqpgc0p.bill ON transaction.transaction_id = bill.transaction_id WHERE transaction.group_id ="
                        + tempGroup_id
                        + " UNION SELECT transaction.transaction_id, group_id, date_created, date_closed, transaction_content, amount, photo_url  "
                        + "FROM omjmf6vzmpqpgc0p.transaction RIGHT JOIN omjmf6vzmpqpgc0p.bill ON transaction.transaction_id = bill.transaction_id WHERE transaction.transaction_id IS NULL";
                try {
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        HashMap<String, String> userAllTransactionJSON = new HashMap<String, String>();
                        userAllTransactionJSON.put("transaction_id", Integer.toString(rs.getInt("transaction_id")));
                        userAllTransactionJSON.put("group_id", Integer.toString(rs.getInt("group_id")));
                        userAllTransactionJSON.put("date_created", (rs.getString("date_created")));
                        userAllTransactionJSON.put("date_closed", (rs.getString("date_closed")));
                        userAllTransactionJSON.put("transaction_content", (rs.getString("transaction_content")));
                        userAllTransactionJSON.put("amount", Double.toString(rs.getDouble("amount")));
                        userAllTransactionJSON.put("photo_url", (rs.getString("photo_url")));

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
}