package com.tie.controller;

import com.tie.model.dao.User;
import com.tie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public void addUser(@RequestParam String phoneNumber){
        userService.addUser(phoneNumber);
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
