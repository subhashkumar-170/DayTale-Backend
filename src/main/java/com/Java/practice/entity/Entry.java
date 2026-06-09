package com.Java.practice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document (collection = "entries")
public class Entry {
    @Id
    private String id;
    private  String title;
    private String content;
    private String mood;
    private LocalDateTime date;
    private String username;

    private String photoLink;

}
