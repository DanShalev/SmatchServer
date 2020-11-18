package com.tie.service;

import com.tie.model.dao.User;
import com.tie.repository.UserRepository;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User verifyUserExists(String userId) {
        return userRepository.findUserById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("User %s doesn't exist.", userId)));
    }
}
