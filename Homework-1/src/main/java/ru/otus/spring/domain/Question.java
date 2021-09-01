package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private final List<Answer> answers;
    private int answersCount;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
        answersCount = answers.size();
    }

    public Question(String question) {
        this.question = question;
        answers = new ArrayList<>();
        answersCount = 0;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answersCount++;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    @Override
    public String toString() {
        return question + answers + System.lineSeparator();
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
