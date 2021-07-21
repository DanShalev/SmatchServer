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

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getUser")
    public User getUser(@RequestHeader String userId) {
        return userService.verifyUserExists(userId);
    }

    @PostMapping("/registerUserForPushNotifications")
    public User registerUserForPushNotifications(@RequestBody String token, @RequestHeader String userId) throws JSONException {
        return userService.registerForPushNotifications(userId, token);
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/setUserImage/{imageNum}")
    public void setUserImage(@RequestBody String image, @PathVariable int imageNum, @RequestHeader String userId) {
        userService.setUserImage(image, imageNum, userId);
    }

    @GetMapping("/removeUserImage/{imageNum}")
    public void removeUserImage(@PathVariable int imageNum, @RequestHeader String userId) {
        userService.removeUserImage(imageNum, userId);
    }
}
