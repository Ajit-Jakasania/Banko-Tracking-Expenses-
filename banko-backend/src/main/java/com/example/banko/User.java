package com.example.banko;

import java.sql.*;
import java.util.ArrayList;

public class User {

    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private int address_id;
    private String username;
    private String hashed_password;
    private int birth_month;
    private int birth_day;
    private int birth_year;
    private String date_created;


    public User(int user_id, String first_name, String last_name, String email, String phone_number, int address_id, String username, String hashed_password, int birth_month, int birth_day, int birth_year, String date_created) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address_id = address_id;
        this.username = username;
        this.hashed_password = hashed_password;
        this.birth_month = birth_month;
        this.birth_day = birth_day;
        this.birth_year = birth_year;
        this.date_created = date_created;

    }

    public int registerUser() throws SQLException {

        int status = 0;
        Connection connection = BankoBackendServer.connection;
        //Phone number is 11 digits
        boolean checkPhoneNumber = false;
        boolean uniUsername = false;
        checkPhoneNumber = phoneNumberDigits(this.phone_number);

        //Check if the username is unique
        uniUsername = uniqueUsername(this.username, connection);


        if(checkPhoneNumber)
        {
            if(uniUsername)
            {
                //Check the country_id and city_id that match from the country and city tables in MySQL

                //Hashed password function which returns a string of length 128

                //Store the data in the user table in MySQL
                //Check if the data was stored properly
            }
            else
            {
                //Return to front-end, username is not unique
                status = 2;
                return status;
            }
        }
        else
        {
            //Return to front-end, phoneNumber is not 11 digits
            status = 1;
            return status;
        }


        //Return value to front-end, fail=0, pass=1

        return status;
    }

    private static boolean uniqueUsername(String username, Connection connection) throws SQLException {
        String usernameInDatabase = "";
        String query = "SELECT username FROM omjmf6vzmpqpgc0p.user";
        try {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                usernameInDatabase = rs.getString("username");
                if(usernameInDatabase.equals(username))
                {
                    //Username is not unique
                    return false;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        //Username is unique

        return true;
    }

    private static boolean phoneNumberDigits(String phoneNumber)
    {
        if(phoneNumber.length()==11)
        {
            return true;
        }

        return false;
    }

//    private static void getConnection() throws SQLException {
//
//        if(connection==null)
//        {
//            (Connection) this.connection = DriverManager.getConnection("jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8", "kj2wtrq16ce5fgdd");
//        }
//
//    }

    public static ArrayList getUserData(String username) throws SQLException {

        ArrayList<String> user = new ArrayList<>();
        Connection connection = BankoBackendServer.connection;
        String selectSql = "SELECT * FROM omjmf6vzmpqpgc0p.user WHERE username='"+username+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);
        while(rs.next())
        {
            user.add(Integer.toString(rs.getInt("user_id")));
            user.add(rs.getString("first_name"));
            user.add(rs.getString("last_name"));
            user.add(rs.getString("email"));
            user.add(rs.getString("phone_number"));
            user.add(Integer.toString(rs.getInt("address_id")));
            user.add(rs.getString("username"));
            user.add(rs.getString("hashed_password"));
            user.add(Integer.toString(rs.getInt("birth_month")));
            user.add(Integer.toString(rs.getInt("birth_day")));
            user.add(Integer.toString(rs.getInt("birth_year")));
            user.add(rs.getTimestamp("date_created").toString());
        }
        statement.close();
        return user;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public int getBirth_month() {
        return birth_month;
    }

    public void setBirth_month(int birth_month) {
        this.birth_month = birth_month;
    }

    public int getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(int birth_day) {
        this.birth_day = birth_day;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
