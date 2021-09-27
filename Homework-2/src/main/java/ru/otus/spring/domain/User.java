package ru.otus.spring.domain;

import java.util.Scanner;

public class User {
    private final String firstName, lastName;

    private User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User create() {
        String firstName = "", lastName = "";
        Scanner scanner = new Scanner(System.in);
        while ("".equals(firstName)) {
            System.out.println("Enter your first name:");
            firstName = scanner.nextLine();
        }
        while ("".equals(lastName)) {
            System.out.println("Enter your last name:");
            lastName = scanner.nextLine();
        }
        return new User(firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
