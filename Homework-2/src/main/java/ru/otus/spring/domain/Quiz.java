package ru.otus.spring.domain;

import java.util.List;

public class Quiz {
    private final List<Question> questions;

    private Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public static Quiz create(List<Question> questions) {
        return new Quiz(questions).sort();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    private Quiz sort() {
        questions.sort(Question::compareTo);
        for (Question question : questions) {
            question.getAnswers().sort(Answer::compareTo);
        }
        return this;
    }
}
