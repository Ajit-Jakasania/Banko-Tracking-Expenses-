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
public class AllGroupUserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    @GetMapping(value = "/allGroupUser")
    public @ResponseBody String getAllGroupUser(@RequestParam(required = false, defaultValue = "") int group_id) {
        ArrayList<HashMap<String, String>> allGroupUser = new ArrayList<HashMap<String, String>>();
        String json = null;

        try {
            allGroupUser = AllGroupUser.getGroupUser(group_id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            json = new ObjectMapper().writeValueAsString(allGroupUser);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
