package com.example.banko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class UserLogInController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    // Check the login details of the user
    @PostMapping(value = "/userLogIn")
    public @ResponseBody int userLogin(@RequestBody UserLogIn userLogIn) {
        int id = -1;
        try {
            id = userLogIn.loginUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

}