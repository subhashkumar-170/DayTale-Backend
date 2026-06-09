package com.Java.practice.services;

import com.Java.practice.entity.User;
import com.Java.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
