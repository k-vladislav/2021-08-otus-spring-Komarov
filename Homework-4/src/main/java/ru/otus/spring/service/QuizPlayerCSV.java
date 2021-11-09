package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;

import java.util.Scanner;

@Service
public class QuizPlayerCSV implements QuizPlayer {

    private final Quiz quiz;
    private int score = 0;
    private final int requiredScore;
    private final Messenger msgSrc;

    private QuizPlayerCSV(Quiz quiz, int requiredScore, Messenger msgSrc) {
        this.quiz = quiz;
        this.requiredScore = requiredScore;
        this.msgSrc = msgSrc;
    }

    @Override
    public void play() {
        msgSrc.out("quiz.started");
        for (Question question : quiz.getQuestions()) {
            displayQuestion(question);
            if (hasCorrectAnswer(question)) {
                ask(question);
            } else {
                msgSrc.out("quiz.skip.question");
            }
        }
        msgSrc.out("quiz.finished");
        displayResult();
    }

    private boolean hasCorrectAnswer(Question question) {
        return question.getAnswers().stream().map(Answer::getAnswerId).anyMatch(integer -> integer == question.getCorrectAnswerId());
    }

    private void displayQuestion(Question question) {
        System.out.println(question);
        for (Answer answer : question.getAnswers()) {
            System.out.println(answer);
        }
    }


    private void displayResult() {
        msgSrc.out("quiz.show.score.and.required.score", new String[]{String.valueOf(score), String.valueOf(requiredScore)});
        if (score >= requiredScore) msgSrc.out("quiz.passed");
        else msgSrc.out("quiz.failed");
    }

    private void ask(Question question) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            msgSrc.out("quiz.question.user.input");
            userInput = scanner.nextLine();
        } while (!isUserInputValid(question, userInput));
        msgSrc.out("quiz.correct.answer", new String[]{String.valueOf(question.getCorrectAnswerId())});
        if (question.isCorrectAnswer(Integer.parseInt(userInput))) {
            score++;
        }
        msgSrc.out("quiz.current.score", new String[]{String.valueOf(score)});
    }

    private boolean isUserInputValid(Question question, String userInput) {
        if (userInput.matches("[0-9]+")) {
            int yourAnswer = Integer.parseInt(userInput);
            return yourAnswer >= 1 && yourAnswer <= question.getAnswersCount();
        }
        return false;
    }

    @Override
    public void greetUser() {
        User user = UserService.create(msgSrc);
        msgSrc.out("user.greet", new String[]{String.valueOf(user)});
    }
}
