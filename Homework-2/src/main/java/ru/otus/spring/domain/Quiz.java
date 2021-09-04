package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final List<Question> questions;
    private int questionsCount;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        questionsCount = questions.size();
    }

    public Quiz() {
        questions = new ArrayList<>();
        questionsCount = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        questionsCount++;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void printQuiz() {
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            for (Answer answer : question.getAnswers()) {
                System.out.println("\t" + answer);
            }
        }
    }

    @Override
    public String toString() {
        return questions.toString() + System.lineSeparator();
    }
}
