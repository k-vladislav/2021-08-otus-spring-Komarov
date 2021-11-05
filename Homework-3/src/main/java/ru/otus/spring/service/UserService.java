package ru.otus.spring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

import java.util.Scanner;

@Service
public class UserService {


    //@Bean
    public static User create(Messenger msgService) {
        String firstName = "", lastName = "";
        Scanner scanner = new Scanner(System.in);
        while ("".equals(firstName)) {
            msgService.out("user.enter.firstname");
            firstName = scanner.nextLine();
        }
        while ("".equals(lastName)) {
            msgService.out("user.enter.lastname");
            lastName = scanner.nextLine();
        }
        return User.create(firstName, lastName);
    }
}
