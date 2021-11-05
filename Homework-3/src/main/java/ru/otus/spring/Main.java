package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.QuizPlayerCSV;
import ru.otus.spring.service.UserService;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        QuizPlayerCSV quizPlayerCSV = ctx.getBean(QuizPlayerCSV.class);

        quizPlayerCSV.greetUser();

        quizPlayerCSV.play();
    }
}

