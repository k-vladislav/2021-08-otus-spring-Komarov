package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.QuizPlayerCSV;
import ru.otus.spring.service.QuizServiceCSV;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        QuizServiceCSV quizServiceCSV = context.getBean(QuizServiceCSV.class);

        Quiz quiz = quizServiceCSV.getQuiz();
        User user = User.create();
        QuizPlayerCSV quizPlayerCSV = QuizPlayerCSV.create(user, quiz);
        quizPlayerCSV.play();
    }
}

