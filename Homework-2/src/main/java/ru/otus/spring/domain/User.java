package ru.otus.spring.domain;

public class User {
    private final String firstName, lastName;

    /**
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
