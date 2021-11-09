package ru.otus.spring.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.service.QuizPlayer;

@ShellComponent

public class ShellQuizImpl implements ShellQuiz {

    private final QuizPlayer quizPlayer;
    private boolean isLoggedIn;

    public ShellQuizImpl(QuizPlayer quizPlayer) {
        this.quizPlayer = quizPlayer;
    }


    @Override
    @ShellMethod(value = "login", key = "quiz-login")
    public void login() {
        quizPlayer.greetUser();
        isLoggedIn = true;
    }

    @Override
    @ShellMethod(value = "Begin quiz", key = "quiz-play")
    @ShellMethodAvailability("isLoggedIn")
    public void playQuiz() {
        quizPlayer.play();
    }

    public Availability isLoggedIn() {
        return isLoggedIn ? Availability.available() : Availability.unavailable("First run quiz-login to login user");
    }

}
