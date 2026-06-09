package com.Java.practice.repository;

import com.Java.practice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String> {

    User findByUsername(String username);
}
