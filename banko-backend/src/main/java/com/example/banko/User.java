package com.example.banko;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.example.BankoBackendServer;

public class User {

    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String street_name;
    private int zip_code = -1; // default -1 b/c optional and int cannot be null
    private String city_name;
    private String country_name;
    private String username;
    private String hashed_password;
    private int birth_month;
    private int birth_day;
    private int birth_year;
    private String date_created;

    public User() {
        this.first_name = null;
        this.last_name = null;
        this.email = null;
        this.phone_number = null;
        this.street_name = null;
        this.zip_code = -1;
        this.city_name = null;
        this.country_name = null;
        this.username = null;
        this.hashed_password = null;
        this.birth_month = -1;
        this.birth_day = -1;
        this.birth_year = -1;
    }

    // all values sent in
    public User(String first_name, String last_name, String email, String phone_number, String street_name,
            int zip_code, String city_name, String country_name, String username, String hashed_password,
            int birth_month, int birth_day, int birth_year) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.street_name = street_name;
        this.zip_code = zip_code;
        this.city_name = city_name;
        this.country_name = country_name;
        this.username = username;
        this.hashed_password = hashed_password;
        this.birth_month = birth_month;
        this.birth_day = birth_day;
        this.birth_year = birth_year;

    }

    // all values but phonenumber sent in
    public User(String first_name, String last_name, String email, String street_name, int zip_code, String city_name,
            String country_name, String username, String hashed_password, int birth_month, int birth_day,
            int birth_year) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.street_name = street_name;
        this.zip_code = zip_code;
        this.city_name = city_name;
        this.country_name = country_name;
        this.username = username;
        this.hashed_password = hashed_password;
        this.birth_month = birth_month;
        this.birth_day = birth_day;
        this.birth_year = birth_year;

    }

    // all values but zipcode sent in
    public User(String first_name, String last_name, String email, String phone_number, String street_name,
            String city_name, String country_name, String username, String hashed_password, int birth_month,
            int birth_day, int birth_year) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.street_name = street_name;
        this.city_name = city_name;
        this.country_name = country_name;
        this.username = username;
        this.hashed_password = hashed_password;
        this.birth_month = birth_month;
        this.birth_day = birth_day;
        this.birth_year = birth_year;

    }

    // all values but phone and zipcode sent in
    public User(String first_name, String last_name, String email, String street_name, String city_name,
            String country_name, String username, String hashed_password, int birth_month, int birth_day,
            int birth_year) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.street_name = street_name;
        this.city_name = city_name;
        this.country_name = country_name;
        this.username = username;
        this.hashed_password = hashed_password;
        this.birth_month = birth_month;
        this.birth_day = birth_day;
        this.birth_year = birth_year;

    }

    /**
     * bullshit hash fucntion
     * 
     * @param password
     * @return
     */
    public static String hashString(String password) {

        char[] temp = password.toCharArray();
        int asciiArr[];
        asciiArr = new int[temp.length];

        String returnVal = "";

        for (int i = 0; i < temp.length; i++) {
            asciiArr[i] = temp[i] * 3 - 2;
            returnVal += (char) asciiArr[i];
        }

        return returnVal;
    }

    public static boolean authenticatePassword(String username, String password) {
        String tempPw = hashString(password);
        Connection connection = BankoBackendServer.connection;

        String query = "SELECT hashed_password FROM omjmf6vzmpqpgc0p.user WHERE username = '" + username + "'";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);

            // if they are equal, return true
            if (rs.next())
                if (rs.getString("hashed_password").equals(tempPw))
                    return true;

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int registerUser() throws SQLException {

        int status = 0;
        Connection connection = BankoBackendServer.connection;

        if (phone_number == null || phoneNumberDigits(this.phone_number)) { // phonenumber is optional
            if (uniqueUsername(this.username)) {

                // gets our country and city ids, and then generates our address id.
                int country_id = getCountryId(country_name, connection);
                int city_id = getCityId(city_name, connection);

                // if zip empty, -1 sent to createAddressID
                int address_id = createAddressID(country_id, city_id, street_name, zip_code, connection);

                String hashed_password = hashString(this.hashed_password);

                // if phone number given, cool, if not, cool
                String query;
                if (phone_number != null) {
                    query = "INSERT INTO omjmf6vzmpqpgc0p.user (first_name, last_name, "
                            + "email, phone_number, address_id, username, hashed_password, birth_month, birth_day, birth_year, date_created) VALUES "
                            + "('" + first_name + "', '" + last_name + "', '" + email + "', '" + phone_number + "', "
                            + address_id + ", '" + username + "', '" + hashed_password + "', " + birth_month + ", "
                            + birth_day + ", " + birth_year + ", NOW())";
                } else {
                    query = "INSERT INTO omjmf6vzmpqpgc0p.user (first_name, last_name, "
                            + "email, address_id, username, hashed_password, birth_month, birth_day, birth_year, date_created) VALUES "
                            + "('" + first_name + "', '" + last_name + "', '" + email + "', " + address_id + ", '"
                            + username + "', '" + hashed_password + "', " + birth_month + ", " + birth_day + ", "
                            + birth_year + ", NOW())";
                }

                try {
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

                    statement.executeUpdate(query);

                    status = 3;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                // Return to front-end, username is not unique
                status = 2;
            }
        } else {
            // Return to front-end, phoneNumber is not 11 digits
            status = 1;
        }

        // Return value to front-end, fail=0, pass=1

        return status;
    }

    /**
     * Gets the country id of the specified country, and returns it. If country not
     * found, which should be impossible unless they are messing with the http
     * request, then function returns -1
     * 
     * @param country_name
     * @param connection
     * @return
     */
    private static int getCountryId(String country_name, Connection connection) {
        String query = "SELECT country_id FROM omjmf6vzmpqpgc0p.country WHERE country_name = '" + country_name + "'";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);

            if (rs.next())
                return Integer.parseInt(rs.getString("country_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets the city id of the specified city, and returns it. If city not found,
     * which is impsosible unless they mess with http request, then returns -1 as
     * error
     * 
     * @param city_name
     * @param connection
     * @return
     */
    private static int getCityId(String city_name, Connection connection) {
        String query = "SELECT city_id FROM omjmf6vzmpqpgc0p.city WHERE city_name = '" + city_name + "'";

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);

            if (rs.next())
                return Integer.parseInt(rs.getString("city_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Utilziing the address info, we make a new address input and return that id,
     * or -1 if error
     * 
     * @param country_id
     * @param city_id
     * @param street_name
     * @param zip_code
     * @param connection
     * @return
     */
    private static int createAddressID(int country_id, int city_id, String street_name, int zip_code,
            Connection connection) {
        String query;

        if (zip_code != -1) {
            query = "INSERT INTO omjmf6vzmpqpgc0p.address (street_name, zip_code, city_id, country_id) VALUES ('"
                    + street_name + "', " + zip_code + ", " + city_id + ", " + country_id + ")";
        } else {
            query = "INSERT INTO omjmf6vzmpqpgc0p.address (street_name, city_id, country_id) VALUES ('" + street_name
                    + "', " + city_id + ", " + country_id + ")";

        }

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            statement.executeUpdate(query);

            // gets last inserted key, aka the address id we just inserted into the table

            query = "SELECT LAST_INSERT_ID()";
            ResultSet rs = statement.executeQuery(query);

            // DOUBLE CHECK THIS SHIT
            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    private static boolean uniqueUsername(String username) throws SQLException {
        Connection connection = BankoBackendServer.connection;
        String usernameInDatabase = "";
        String query = "SELECT username FROM omjmf6vzmpqpgc0p.user WHERE username = '" + username + "'";
        try {

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                usernameInDatabase = rs.getString("username");
                if (usernameInDatabase.equals(username)) {
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

    private static boolean phoneNumberDigits(String phoneNumber) {
        if (phoneNumber.length() == 11) {
            return true;
        }

        return false;
    }

    public static HashMap<String, String> getUserData(String username) throws SQLException {

        Connection connection = BankoBackendServer.connection;

        // TODO: try catch block
        String selectSql = "SELECT * FROM omjmf6vzmpqpgc0p.user WHERE username='" + username + "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);

        HashMap<String, String> userJSON = new HashMap<String, String>();

        if (rs.next()) {
            userJSON.put("user_id", Integer.toString(rs.getInt("user_id")));
            userJSON.put("first_name", (rs.getString("first_name")));
            userJSON.put("last_name", (rs.getString("last_name")));
            userJSON.put("email", (rs.getString("email")));
            userJSON.put("phone_number", (rs.getString("phone_number")));
            userJSON.put("address_id", (Integer.toString(rs.getInt("address_id"))));
            userJSON.put("username", (rs.getString("username")));
            userJSON.put("hashed_password", (rs.getString("hashed_password")));
            userJSON.put("birth_month", (Integer.toString(rs.getInt("birth_month"))));
            userJSON.put("birth_day", (Integer.toString(rs.getInt("birth_day"))));
            userJSON.put("birth_year", (Integer.toString(rs.getInt("birth_year"))));
            userJSON.put("date_created", (rs.getString("date_created")));

        }
        statement.close();

        return userJSON;
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

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
