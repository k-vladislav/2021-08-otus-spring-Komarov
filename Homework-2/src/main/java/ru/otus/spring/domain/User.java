package ru.otus.spring.domain;

public class User {
    private final String firstName, lastName;

    private User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User create(String firstName, String lastName) {
        return new User(firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
