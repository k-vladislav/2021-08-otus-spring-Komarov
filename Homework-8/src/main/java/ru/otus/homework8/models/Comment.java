package ru.otus.homework8.models;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
public class Comment {

    @Id
    private String id;

    private String comment;
    private Book book;

    public Comment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "id = " + id + ": " + comment;
    }
}