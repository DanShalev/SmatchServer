package com.tie.controller;

import com.tie.model.dao.User;
import com.tie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/registerUserForPushNotifications")
    public User registerUserForPushNotifications(@RequestBody String token, @RequestHeader String userId) throws JSONException {
        return userService.registerForPushNotifications(userId, token);
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
