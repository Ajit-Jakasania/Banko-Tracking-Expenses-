package com.example.banko;

import java.sql.*;

public class CreateBankingGroup {

    private int group_id;
    private String group_name;
    private String date_created;
    private int user_id;
    private String useranme;

    public CreateBankingGroup(String group_name, String useranme) {
        this.group_name = group_name;
        this.useranme = useranme;
    }

    public CreateBankingGroup(int group_id, String group_name, String date_created, int user_id, String useranme) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.date_created = date_created;
        this.user_id = user_id;
        this.useranme = useranme;
    }

    public int createBankGroup() throws SQLException {

        int groupCreated = 0;
        Connection connection = BankoBackendServer.connection;
        user_id = getUserId(this.useranme, connection);
        if (uniqueGroup(group_name, connection)) {
            // Group is created
            if (createGroup(group_name, connection)) {
                group_id = getGroupId(group_name, connection);
                if (userJoinGroup(user_id, group_id, connection)) {
                    // User join the group created
                    groupCreated = 1;
                }
            }
        } else {
            // Group name is not unique
            groupCreated = 2;
        }

        connection.close();
        return groupCreated;
    }

    private boolean userJoinGroup(int user_id, int group_id, Connection connection) {
        boolean flag = false;
        String query = "INSERT INTO user_in_group(user_id, group_id,date_joined) VALUES (" + user_id + "," + group_id
                + ", now())";
        Statement statement;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.execute(query);
            flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    private boolean createGroup(String group_name, Connection connection) {
        boolean flag = false;

        String query = "INSERT INTO group_list (group_name, date_created) VALUES ('" + group_name + "', now())";
        Statement statement;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.execute(query);
            flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    private boolean uniqueGroup(String group_name, Connection connection) {
        String groupInDatabase = "";
        String query = "SELECT group_name FROM omjmf6vzmpqpgc0p.group_list";
        try {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                groupInDatabase = rs.getString("group_name");
                if (groupInDatabase.equals(group_name)) {
                    // Username is not unique
                    return false;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        // Username is unique
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

    public String getUseranme() {
        return useranme;
    }

    public void setUseranme(String useranme) {
        this.useranme = useranme;
    }
}
