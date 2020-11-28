package com.example.banko;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SendMessageController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

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
    @PostMapping(value = "/message")
    public @ResponseBody String sendMessage(@RequestBody Message message) {
        int status = 0;
        try {
            status = message.sendGroupMessage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (status == 1)
            System.out.println("Message sent");
        else
            System.out.println("Message not sent");
    }

}
