package com.example.banko;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JoinBankingGroupController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @PostMapping(value = "/joinGroup")
    public @ResponseBody String joinGroup(@RequestBody CreateBankingGroup createBankingGroup) {

        try {
            if (createBankingGroup.userJoinGroup())
                return "User joined group successfully";
            else
                return "User failed to join group: Check group_name entered!";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Check joinGroupController post method (backend)";
    }
}
