package com.example.banko;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
public class PaymentController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @PostMapping(value = "/initializePayment")
    public @ResponseBody String initializePayment(@RequestBody Payment payment) {
        int status = 0;
        try {
            status = payment.initializePayment();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (status == 1)
            return "payment initialized for inputted user";
        else
            return "Not initialized";
    }

    @PostMapping(value = "/makePayment")
    public @ResponseBody String makePayment(@RequestBody Payment payment) {
        try {
            if (payment.makePayment())
                return "successful payment";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "unsuccessful payment";

    }

}
