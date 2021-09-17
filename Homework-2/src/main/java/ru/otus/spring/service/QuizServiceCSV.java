package ru.otus.spring.service;

import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuizDao;
import ru.otus.spring.domain.*;

import java.io.*;
import java.util.Scanner;

@Configuration
public class QuizServiceCSV implements QuizService {
    private final QuizDao dao;
    private final Quiz quiz;
    private User user;
    private int correctAnswers = 0;
    private final int CORRECT_ANSWERS_THRESHOLD = 5;

    @Autowired
    public QuizServiceCSV(QuizDao dao) {
        this.dao = dao;
        quiz = new Quiz();
    }

    private void addUser() {
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
        this.user = new User(firstName, lastName);
        System.out.println("User added: " + user);
    }

    private void processQuiz() {
        addUser();
        loadQuiz();
        System.out.println("Start quiz!");
        Scanner scanner = new Scanner(System.in);
        int yourAnswer;
        for (Question question : quiz.getQuestions()) {
            question.display();
            if (question.isCorrectAnswerAdded()) {
                System.out.print("Your answer: ");
                yourAnswer = scanner.nextInt();
                System.out.println("Your input is " + yourAnswer + ", correct ID is " + question.getCorrectAnswerId());
                if (yourAnswer == question.getCorrectAnswerId()) {
                    correctAnswers++;
                    System.out.println("Good! Score: " + correctAnswers);
                }
            } else {
                System.out.println("Skip question as no correct answer added");
            }
        }
        System.out.println("Quiz finished!");
    }

    private void printResult() {
        System.out.println("Score required: " + CORRECT_ANSWERS_THRESHOLD + ", your score: " + correctAnswers);
        if (correctAnswers >= CORRECT_ANSWERS_THRESHOLD) {
            System.out.println("Quiz passed!");
        } else {
            System.out.println("Quiz failed");
        }
    }

    @Override
    public void startQuiz() {
        processQuiz();
        printResult();
    }


    private void loadQuiz() {
        //todo initialize each time new?
        InputStream quizInputStream = dao.loadQuiz();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(quizInputStream));
        try (CSVParser csvRecords = CSVParser.parse(bufferedReader, CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().build())) {
            for (CSVRecord csvRecord : csvRecords) {
                String type = csvRecord.get("TYPE"); //todo headerNames to configFile
                int qID = Integer.parseInt(csvRecord.get("QID"));
                int aID = Integer.parseInt(csvRecord.get("AID"));
                String value = csvRecord.get("VALUE");
                if ("Q".equals(type)) {
                    quiz.addQuestion(new Question(value, qID, aID));
                } else if ("A".equals(type)) {
                    quiz.addAnswer(new Answer(value, qID, aID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
