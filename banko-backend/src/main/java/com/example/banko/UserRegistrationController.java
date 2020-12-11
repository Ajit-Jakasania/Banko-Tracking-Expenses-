package com.example.banko;

import java.sql.SQLException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
public class UserRegistrationController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    /*
     * List the user details
     */
    @GetMapping(value = "/user")
    public @ResponseBody String getUser(@RequestParam(required = false, defaultValue = "") String username) {
        HashMap<String, String> user = new HashMap<String, String>();
        String json = null;

        try {
            user = User.getUserData(username);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            json = new ObjectMapper().writeValueAsString(user);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /*
     * 
     * Needs to take in user details as such first_name, last_name, email,
     * phone_number, street_name, zip_code, city_name, country_name, username,
     * password, birth_month, birth_day, birth_year
     * 
     * Note: phone_number and zipcode are the ONLY values that may be empty / null.
     * 
     * Address id input requires that we first send in all the address details. So,
     * we take all the address details here, as the registeration page will take the
     * address details of the user. We will then process the address data first, get
     * the city id and the country id, and then utilize those in creating the user.
     * 
     * That is why we take in the city and country data here.
     * 
     * user_id is auto increment, and so is address_id, they will be just created
     * for us.
     * 
     * 
     */
    @PostMapping(value = "/userRegister")
    public @ResponseBody String addNewUser(@RequestBody User user) {
        int status = 0;
        try {
            status = user.registerUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (status == 1)
            return "Failed as phoneNumber is not 11 digits!";
        else if (status == 2)
            return "Failed as username is not unique!";
        else if (status == 3)
            return "User Successfully Registered!";
        return "Check the userRegister post method (backend)";
    }

}
