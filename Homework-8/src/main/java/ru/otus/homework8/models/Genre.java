package ru.otus.homework8.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document
public class Genre {

    @Id
    private String id;
    private String genre;
    private Set<Book> books;

    public Genre(String genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "id = " + id + ": " + genre;
    }
}
