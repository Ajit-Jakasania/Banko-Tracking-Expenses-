package com.example.banko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserLogInController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    /*
     * List the user details
     */
    // @GetMapping(value = "/userLogIn")
    // public @ResponseBody
    // ArrayList<String> getUser(@RequestParam(required = false, defaultValue = "")
    // String username) {
    //
    // }

    // Check the login details of the user
    @PostMapping(value = "/userLogIn")
    public @ResponseBody String userLogin(@RequestBody UserLogIn userLogIn) {
        boolean login = false;
        try {
            login = userLogIn.loginUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (login)
            return "Login Successfully";
        else
            return "Login Failed! Check your username and password again!";
    }

}
