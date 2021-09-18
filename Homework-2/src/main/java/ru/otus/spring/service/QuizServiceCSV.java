package ru.otus.spring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.dao.QuizDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class QuizServiceCSV implements QuizService {
    private final QuizDao dao;
    private final Quiz quiz;
    private int correctAnswers = 0;
    private int correctAnswerRequired;


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
        User user = new User(firstName, lastName);
        System.out.println("User added: " + user);
    }

    private void playQuiz() {
        addUser();
        loadQuiz();
        System.out.println("Start quiz!");
        for (Question question : quiz.getQuestions()) {
            playQuestion(question);
        }
        System.out.println("Quiz finished!");
    }

    private void playQuestion(Question question) {
        question.display();
        if (question.isCorrectAnswerAdded()) {
            processQuestion(question);
        } else {
            System.out.println("Skip question as no correct answer added");
        }
    }

    private void processQuestion(Question question) {
        Scanner scanner = new Scanner(System.in);
        int yourAnswer;
        String input;
        boolean isInputValid;
        do {
            System.out.print("Your answer: ");
            input = scanner.nextLine();
            isInputValid = validateInput(question, input);
        } while (!isInputValid);
        yourAnswer = Integer.parseInt(input);
        System.out.println("Your input is " + yourAnswer + ", correct ID is " + question.getCorrectAnswerId());
        if (yourAnswer == question.getCorrectAnswerId()) {
            correctAnswers++;
            System.out.println("Good! Score: " + correctAnswers);
        }
    }

    private boolean validateInput(Question question, String input) {
        if (input.matches("[0-9]+")) {
            int yourAnswer = Integer.parseInt(input);
            return yourAnswer >= 1 && yourAnswer <= question.getAnswersCount();
        }
        return false;
    }

    private void printResult() {
        System.out.println("Score required: " + correctAnswerRequired + ", your score: " + correctAnswers);
        if (correctAnswers >= correctAnswerRequired) {
            System.out.println("Quiz passed!");
        } else {
            System.out.println("Quiz failed");
        }
    }

    @Override
    public void startQuiz() {
        playQuiz();
        printResult();
    }


    private void loadQuiz() {
        InputStream quizInputStream = dao.loadQuiz();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(quizInputStream));
        try (CSVParser csvRecords = CSVParser.parse(bufferedReader, CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().build())) {
            for (CSVRecord csvRecord : csvRecords) {
                String type = csvRecord.get("TYPE");
                int qID = Integer.parseInt(csvRecord.get("QID"));
                int aID = Integer.parseInt(csvRecord.get("AID"));
                String value = csvRecord.get("VALUE");
                if ("Q".equals(type)) {
                    quiz.addQuestion(new Question(value, qID, aID));
                } else if ("A".equals(type)) {
                    quiz.addAnswer(new Answer(value, qID, aID));
                }
            }
            quiz.matchAnswers();
            quiz.sort();
            correctAnswerRequired = AppConfig.getRequiredScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
