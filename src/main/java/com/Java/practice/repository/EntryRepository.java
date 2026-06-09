package com.Java.practice.repository;

import com.Java.practice.entity.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntryRepository extends MongoRepository<Entry, String> {

    List<Entry> findByUsername(String username);
}
