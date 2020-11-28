package com.example.banko;

import java.sql.*;
import java.util.ArrayList;

public class User {

    private int user_id;
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

    // all values sent in
    public User(int user_id, String first_name, String last_name, String email, String phone_number, String street_name,
            int zip_code, String city_name, String country_name, String username, String hashed_password,
            int birth_month, int birth_day, int birth_year, String date_created) {
        this.user_id = user_id;
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
        this.date_created = date_created;

    }

    // all values but phonenumber sent in
    public User(int user_id, String first_name, String last_name, String email, String street_name, int zip_code,
            String city_name, String country_name, String username, String hashed_password, int birth_month,
            int birth_day, int birth_year, String date_created) {
        this.user_id = user_id;
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
        this.date_created = date_created;

    }

    // all values but zipcode sent in
    public User(int user_id, String first_name, String last_name, String email, String phone_number, String street_name,
            String city_name, String country_name, String username, String hashed_password, int birth_month,
            int birth_day, int birth_year, String date_created) {
        this.user_id = user_id;
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
        this.date_created = date_created;

    }

    // all values but phone and zipcode sent in
    public User(int user_id, String first_name, String last_name, String email, String street_name, String city_name,
            String country_name, String username, String hashed_password, int birth_month, int birth_day,
            int birth_year, String date_created) {
        this.user_id = user_id;
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
        this.date_created = date_created;

    }

    public int registerUser() throws SQLException {

        int status = 0;
        Connection connection = BankoBackendServer.connection;

        if (phone_number == null || phoneNumberDigits(this.phone_number)) { // phonenumber is optional
            if (uniqueUsername(this.username, connection)) {

                // gets our country and city ids, and then generates our address id.
                int country_id = getCountryId(country_name, connection);
                int city_id = getCityId(city_name, connection);

                // if zip empty, -1 sent to createAddressID
                int address_id = createAddressID(country_id, city_id, street_name, zip_code, connection);

                // Hashed password function which returns a string of 128 length, first 15 are
                // the salt, then a $ as a delimiter, and then last 112 are the hashed pass. we
                // store this as
                // salt$hashedpassword
                // this way the same password utilizing a different salt will look different in
                // the db. To authenticate, we pull the hashed password, pull first half of the
                // string using $ delimiter, then we hash the inputted password using the salt
                // and the password input. If it equals the second half of the string past the $
                // delimiter, then we have authentiated the pw

                // suppose now the hased password is stored in the object variable
                // hashed_password

                // if phone number given, cool, if not, cool
                String query;
                if (phone_number != null) {
                    query = "INSERT INTO omjmf6vzmpqpgc0p.user(first_name, last_name, "
                            + "email, phone_number, address_id, username, hashed_password, birth_month, birth_day, birth_year) VALUES "
                            + "('" + first_name + "', '" + last_name + "', '" + email + "', '" + phone_number + "', "
                            + address_id + ", '" + username + "', '" + hashed_password + "', " + birth_month + ", "
                            + birth_day + ", " + birth_year + ", NOW())";
                } else {
                    query = "INSERT INTO omjmf6vzmpqpgc0p.user(first_name, last_name, "
                            + "email, address_id, username, hashed_password, birth_month, birth_day, birth_year) VALUES "
                            + "('" + first_name + "', '" + last_name + "', '" + email + "', " + address_id + ", '"
                            + username + "', '" + hashed_password + "', " + birth_month + ", " + birth_day + ", "
                            + birth_year + ", NOW())";
                }

                try {
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

                    statement.executeQuery(query);

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
            query = "INSERT INTO omjmf6vzmpqpgc0p.address(street_name, zip_code, city_id, country_id) VALUES ('"
                    + street_name + "', " + zip_code + ", " + city_id + ", " + country_id + ")";
        } else {
            query = "INSERT INTO omjmf6vzmpqpgc0p.address(street_name, city_id, country_id) VALUES ('" + street_name
                    + ", " + city_id + ", " + country_id + ")";

        }

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(query);

            // gets last inserted key, aka the address id we just inserted into the table
            rs = statement.getGeneratedKeys();
            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    private static boolean uniqueUsername(String username, Connection connection) throws SQLException {
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

    public static ArrayList getUserData(String username) throws SQLException {

        ArrayList<String> user = new ArrayList<>();
        Connection connection = BankoBackendServer.connection;
        String selectSql = "SELECT * FROM omjmf6vzmpqpgc0p.user WHERE username='" + username + "'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(selectSql);
        while (rs.next()) {
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
