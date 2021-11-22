package ru.otus.Homework5.domain;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private final String title;
    private List<Author> authors;
    private List<Genre> genres;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
