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
public class UserGroupController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @GetMapping(value = "/userGroups")
    public @ResponseBody String getUserGroups(@RequestParam(required = false, defaultValue = "") int user_id) {
        ArrayList<HashMap<String, String>> userGroup = new ArrayList<HashMap<String, String>>();
        String json = null;

        try {
            userGroup = UserGroup.getUserGroup(user_id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            json = new ObjectMapper().writeValueAsString(userGroup);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
