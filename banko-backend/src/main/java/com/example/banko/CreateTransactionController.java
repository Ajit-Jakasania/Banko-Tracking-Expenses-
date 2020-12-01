package com.example.banko;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping(value = "/bill")
    public @ResponseBody String addBill(@RequestBody Bill bill) {
        int status = 0;
        try {
            status = bill.createBill();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (status == 1)
            return "Bill made";
        else
            return "Bill not made";
    }

    @GetMapping(value = "/getTransactions")
    public @ResponseBody String getTransactions(@RequestParam int user_id) {
        ArrayList<HashMap<String,String>> userTransaction = new ArrayList<HashMap<String,String>>();
        String json = null;

        userTransaction = Transaction.getUserTransactions(user_id);

        try {
            json = new ObjectMapper().writeValueAsString(userTransaction);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping(value = "/closeTransaction")
    public @ResponseBody String closeTransaction(@RequestBody Transaction transaction) {
        int status = 0;
        try {
            transaction.closeTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (status == 1)
            return "Transaction closed";
        else
            return "Transaction not closed";
    }

}
