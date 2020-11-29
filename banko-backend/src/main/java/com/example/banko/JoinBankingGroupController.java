package com.example.banko;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class JoinBankingGroupController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @PostMapping(value = "/joinGroup")
    public @ResponseBody String joinGroup(@RequestBody CreateBankingGroup createBankingGroup) {
        int status = 0;
        try {
            if (createBankingGroup.userJoinGroup())
                return "User joined group successfully";
            else
                return "User not joined successfully";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (status == 1)
            return "Transaction made";
        else
            return "Transaction not made";
    }

}
