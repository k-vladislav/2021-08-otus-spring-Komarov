package ru.otus.spring;

import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.*;

import java.util.List;
import java.util.Optional;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuizService quizServiceCSV = context.getBean(QuizServiceCSV.class);

        User user = UserService.create();

        Optional<List<CSVRecord>> quizRawData = quizServiceCSV.getQuizRawData();
        if (quizRawData.isPresent()) {
            List<CSVRecord> csvRecords = quizRawData.get();
            Quiz quiz = QuizBuilderCSV.getBuilder(csvRecords).buildQuiz();
            QuizPlayer quizPlayer = QuizPlayerCSV.create(user,quiz);
            quizPlayer.play();
        } else {
            System.out.println("No CSV records!");
        }



    }
}

