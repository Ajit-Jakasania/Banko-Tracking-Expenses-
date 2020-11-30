package com.example.banko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class BankoBackendServer {

    public static Connection connection;

    public static void main(String[] args) {
        SpringApplication.run(BankoBackendServer.class, args);
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://y5s2h87f6ur56vae.cbetxkdyhwsb.us-east-1.rds.amazonaws.com", "xeka86imtg04uaw8",
                    "kj2wtrq16ce5fgdd");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
