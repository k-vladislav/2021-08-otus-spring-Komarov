package ru.otus.spring.domain;

public class User {
    private final String firstName, lastName;

    /**
     * @param firstName name
     * @param lastName surname
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
