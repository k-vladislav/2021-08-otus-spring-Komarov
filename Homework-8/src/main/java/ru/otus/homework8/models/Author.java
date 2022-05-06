package ru.otus.homework8.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document
public class Author {

    @Id
    private String id;

    private String lastName;
    private Set<Book> books;

    public Author(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "id = " + id + ": " + lastName;
    }
}
