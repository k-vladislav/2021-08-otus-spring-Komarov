package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;

import java.util.Scanner;

@Service
public class QuizPlayerCSV implements QuizPlayer {
    private final User user;
    private final Quiz quiz;
    private int score = 0;
    private final int requiredScore;
    private final MsgService msgSrc;

    private QuizPlayerCSV(User user, Quiz quiz, AppConfig appConfig, MsgService msgSrc) {
        this.user = user;
        this.quiz = quiz;
        this.requiredScore = appConfig.getSettings().getRequiredScore();
        this.msgSrc = msgSrc;
    }

    @Override
    public void play() {
        greetUser();
        msgSrc.out("quiz.started");
        for (Question question : quiz.getQuestions()) {
            question.display();
            if (question.isCorrectAnswerAdded()) {
                ask(question);
            } else {
                msgSrc.out("quiz.skip.question");
            }
        }
        msgSrc.out("quiz.finished");
        displayResult();
    }

    private void displayResult() {
        msgSrc.out("quiz.show.score.and.required.score",new String[]{String.valueOf(score), String.valueOf(requiredScore)});
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
        msgSrc.out("quiz.correct.answer",new String[] {String.valueOf(question.getCorrectAnswerId())});
        if (question.isCorrectAnswer(Integer.parseInt(userInput))) {
            score++;
        }
        msgSrc.out("quiz.current.score",new String[]{String.valueOf(score)});
    }

    private boolean isUserInputValid(Question question, String userInput) {
        if (userInput.matches("[0-9]+")) {
            int yourAnswer = Integer.parseInt(userInput);
            return yourAnswer >= 1 && yourAnswer <= question.getAnswersCount();
        }
        return false;
    }

    private void greetUser() {
        msgSrc.out("user.greet", new String[]{String.valueOf(user)});
    }
}
