package com.example.banko;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SendMessageController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin

    /**
     * Adds message to the message and message content tables
     * 
     * @param message
     * @return
     */
    @PostMapping(value = "/message")
    public @ResponseBody String sendMessage(@RequestBody Message message) {
        int status = 0;
        try {
            message.sendGroupMessage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (status == 1)
            return "Message sent";
        else
            return "Message not sent";
    }

}
