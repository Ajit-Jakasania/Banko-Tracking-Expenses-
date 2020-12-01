package com.example.banko;

import java.sql.*;

public class CreateBankingGroup {

    private int group_id;
    private String group_name;
    private String date_created;
    private int user_id;
    private String username;

    public CreateBankingGroup(String group_name, int user_id) {
        this.group_name = group_name;
        this.user_id = user_id;
    }

//    public CreateBankingGroup(int group_id, String group_name, int user_id) {
//        this.group_id = group_id;
//        this.group_name = group_name;
//        this.user_id = user_id;
//    }

    public int createBankGroup() throws SQLException {

        int groupCreated = 0;
        Connection connection = BankoBackendServer.connection;
        if (uniqueGroup(group_name, connection)) {
            // Group is created
            if (createGroup(group_name, connection)) {
                group_id = getGroupId(group_name, connection);
                if (userJoinGroup()) {
                    // User join the group created
                    groupCreated = 1;
                }
            }
        } else {
            // Group name is not unique
            groupCreated = -1;
        }

        return groupCreated;
    }

    public boolean userJoinGroup() throws SQLException {
        Connection connection = BankoBackendServer.connection;

        String query = "INSERT INTO omjmf6vzmpqpgc0p.user_in_group (user_id, group_id,date_joined) VALUES (" + this.user_id + ","
                + this.group_id + ", now())";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    private boolean createGroup(String group_name, Connection connection) {
        boolean flag = false;

        String query = "INSERT INTO omjmf6vzmpqpgc0p.group_list (group_name, date_created) VALUES ('" + group_name + "', now())";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);
            flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    private boolean uniqueGroup(String group_name, Connection connection) {

        String group_nameInDatabase = "";
        String query = "SELECT group_name FROM omjmf6vzmpqpgc0p.group_list WHERE group_name = '" + group_name + "'";
        try {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                group_nameInDatabase = rs.getString("group_name");
                if(group_nameInDatabase.equals(group_name))
                {
                    // group_name is not unique
                    return false;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        //Group name is unique
        return true;

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

    private int getUserId(String username, Connection connection) {
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

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
