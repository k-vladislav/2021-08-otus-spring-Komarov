package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;

import java.util.Scanner;

public class QuizPlayerCSV implements QuizPlayer {
    private final User user;
    private final Quiz quiz;
    private int score = 0;
    private final int requiredScore;

    public static QuizPlayerCSV create(User user, Quiz quiz,int requiredScore) {
        return new QuizPlayerCSV(user, quiz, requiredScore);
    }

    @Override
    public void play() {
        greetUser();
        System.out.println("Quiz started");
        for (Question question : quiz.getQuestions()) {
            question.display();
            if (question.isCorrectAnswerAdded()) {
                ask(question);
            } else {
                System.out.println("Skip question as no correct answer added");
            }
        }
        System.out.println("Quiz finished");
        displayResult();
    }

    private void displayResult() {
        System.out.println("Your score: " + score + ". Required score: " + requiredScore);
        if (score>=requiredScore) System.out.println("Quiz passed!");
        else System.out.println("Quiz failed!");
    }

    private void ask(Question question) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("Your input: ");
            userInput = scanner.nextLine();
        } while (!isUserInputValid(question, userInput));
        System.out.println("Correct: " + question.getCorrectAnswerId());
        if (question.isCorrectAnswer(Integer.parseInt(userInput))) {
            score++;
        }
        System.out.println("Score: " + score);
    }

    private boolean isUserInputValid(Question question, String userInput) {
        if (userInput.matches("[0-9]+")) {
            int yourAnswer = Integer.parseInt(userInput);
            return yourAnswer >= 1 && yourAnswer <= question.getAnswersCount();
        }
        return false;
    }

    private void greetUser() {
        System.out.println("Hello, " + user);
    }

    private QuizPlayerCSV(User user, Quiz quiz, int requiredScore) {
        this.user = user;
        this.quiz = quiz;
        this.requiredScore = requiredScore;
    }

}
