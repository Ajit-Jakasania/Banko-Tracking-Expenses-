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
    public @ResponseBody int userLogin(@RequestBody UserLogIn userLogIn) {
        int id = -1;
<<<<<<< HEAD

=======
>>>>>>> 3514149a93d2f856996beb473dd1e466e1d8b3f2
        try {
            id = userLogIn.loginUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

}