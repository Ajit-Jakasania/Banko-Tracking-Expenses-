package com.example.banko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserLogInController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    // Check the login details of the user
    @PostMapping(value = "/userLogIn")
<<<<<<< HEAD
    public @ResponseBody String userLogin(@RequestBody UserLogIn userLogIn) {
        boolean login = false;
=======
    public @ResponseBody int userLogin(@RequestBody UserLogIn userLogIn) {
        int id = -1;
>>>>>>> 86f3c0b8a23b1ffe650808700285fc749e7ebceb
        try {
            id = userLogIn.loginUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

}