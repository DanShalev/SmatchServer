package com.tie.service;

import com.tie.model.dao.User;
import com.tie.model.dto.UserFieldDTO;
import com.tie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Boolean addUser(User user) {
        // If user exist, exit
        Optional<User> userOptional = userRepository.findUserById(user.getId());
        if (userOptional.isPresent()) {
            return false;
        }

        // Register user
        userRepository.save(user);
        return true;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User verifyUserExists(String userId) {
        return userRepository.findUserById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("User %s doesn't exist.", userId)));
    }

    public User registerForPushNotifications(String userId, String token) throws JSONException {
        JSONObject json = new JSONObject(token);
        token = json.getString("token");

        User updatedUser = verifyUserExists(userId);
        updatedUser.setPushNotificationToken(token);
        userRepository.save(updatedUser);
        return updatedUser;
    }

    public String getUserToken(String userId) {
        return verifyUserExists(userId).getPushNotificationToken();
    }
}
