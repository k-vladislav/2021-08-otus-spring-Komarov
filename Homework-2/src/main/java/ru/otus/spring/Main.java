package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.service.QuizService;

import java.io.IOException;

@ComponentScan
public class Main {
    public static void main(String[] args) throws IOException {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuizService quizService = context.getBean(QuizService.class);
        //Quiz quiz = quizService.getQuiz();
        Quiz quiz = quizService.getQuizAsInputStream();
        quiz.printQuiz();

    }
}
