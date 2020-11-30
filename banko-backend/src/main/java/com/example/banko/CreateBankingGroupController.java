package com.example.banko;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CreateBankingGroupController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @PostMapping(value = "/createBankGroup")
    public @ResponseBody String createBankingGroup(@RequestBody CreateBankingGroup createGroup) {
        int status = 0;
        try {
            status = createGroup.createBankGroup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (status == 1)
            return "Group Created Successfully!";
        else if (status == 2)
            return "Failed as group name is not unique!";

        return "Check createBankController post method (backend)";
    }
}
