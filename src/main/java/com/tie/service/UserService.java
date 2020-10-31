package com.tie.service;

import com.tie.model.dao.User;
import com.tie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void addUser(String phoneNumber){
        log.info("#addUser - created user with phoneNumber: {}", phoneNumber);
        if (isUserExists(phoneNumber)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        userRepository.save(createUser(phoneNumber));
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    private User createUser(String phoneNumber){
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        return user;
    }

    private boolean isUserExists(String phoneNumber){
        Optional<User> user = userRepository.findAll().stream().filter(currUser -> currUser.getPhoneNumber().equals(phoneNumber)).findAny();
        return user.isPresent();
    }



}
