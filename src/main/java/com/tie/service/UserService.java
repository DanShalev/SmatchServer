package com.tie.service;

import com.tie.model.dao.User;
import com.tie.repository.UserRepository;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User addUser() {
        User user = createUser(UUID.randomUUID().toString());
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private User createUser(String id) {
        User user = new User();
        user.setId(id);
        return user;
    }

}
