package com.tie.controller;

import com.tie.database.mysql.HealthCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @GetMapping("/")
    private String healthCheck() throws SQLException {
        if (HealthCheck.getRemoteConnection(jdbcUrl, user, password)) {
            return "Connected to DB";
        }
        return "No Connection";
    }
}
