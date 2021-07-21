package com.tie.service;

import com.tie.model.dao.User;
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

    public void setUserImage(String image, int imageNum, String userId) {
        User user = verifyUserExists(userId);

        switch (imageNum) {
            case 1 -> user.setImage1(image);
            case 2 -> user.setImage2(image);
            case 3 -> user.setImage3(image);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                         String.format("Invalid Image Location (%s)", imageNum));
        }
        userRepository.save(user);
    }

    public void removeUserImage(int imageNum, String userId) {
        User user = verifyUserExists(userId);

        // Delete image and shift image location accordingly
        switch (imageNum) {
            case 1 -> {
                user.setImage1(user.getImage2());
                user.setImage2(user.getImage3());
                user.setImage3(null);
            }
            case 2 -> {
                user.setImage2(user.getImage3());
                user.setImage3(null);
            }
            case 3 -> user.setImage3(null);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Invalid Image Location (%s)", imageNum));
        }
        userRepository.save(user);
    }
}
