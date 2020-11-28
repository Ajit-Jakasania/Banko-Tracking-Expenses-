package com.example.banko;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CreateTransactionController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @PostMapping(value = "/transaction")
    public @ResponseBody String sendMessage(@RequestBody Transaction transaction) {
        int status = 0;
        try {
            transaction.createTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (status == 1)
            return "Transaction made";
        else
            return "Transaction not made";
    }

    // TODO: ADD controller for closing a transaction
}
