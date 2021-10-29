package ru.otus.spring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

import java.util.Scanner;

@Service
public class UserService {

    @Bean
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
        return User.create(firstName, lastName);
    }
}
