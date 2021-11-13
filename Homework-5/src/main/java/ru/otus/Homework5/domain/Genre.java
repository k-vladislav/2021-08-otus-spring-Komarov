package ru.otus.Homework5.domain;

import lombok.Data;

@Data
public class Genre {
    private final String genre;

    @Override
    public String toString() {
        return genre;
    }
}
