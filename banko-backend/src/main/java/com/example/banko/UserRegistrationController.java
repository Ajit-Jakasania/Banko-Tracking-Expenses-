package com.example.banko;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserRegistrationController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    /*
        List the user details
     */
    @GetMapping(value = "/user")
    public @ResponseBody
    ArrayList<String> getUser(@RequestParam(required = false, defaultValue = "") String username) {
        ArrayList<String> user = new ArrayList<>();
        try {
            user = User.getUserData(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @PostMapping(value = "/user")
    public @ResponseBody
    String addNewUser(@RequestBody User user) {
        int status = 0;
        try {
            status = user.registerUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(status==1)
            return "Failed as phoneNumber is not 11 digits!";
        else if(status==2)
            return "Failed as username is not unique!";
        else if (status==3)
            return "User Successfully Registered!";
        return "";
    }

}
