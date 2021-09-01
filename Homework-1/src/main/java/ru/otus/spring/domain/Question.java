package ru.otus.spring.domain;

import java.util.List;

public class Question {
    private final String question;
    private final List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return question+answers+System.lineSeparator();
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
