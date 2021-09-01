package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.service.QuizService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        Quiz quiz = quizService.getQuiz();
        quiz.printQuiz();

    }
}
