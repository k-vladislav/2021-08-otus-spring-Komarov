package ru.otus.Homework5.domain;

import lombok.Data;

@Data
public class Author {
    private final String lastName;

    @Override
    public String toString() {
        return lastName;
    }
}
