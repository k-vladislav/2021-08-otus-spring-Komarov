package ru.otus.spring.domain;

import java.util.List;

public class Quiz {
    private final List<Question> questions;


    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public void printQuiz(){
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            for (Answer answer : question.getAnswers()) {
                System.out.println("\t"+answer);
            }
        }
    }

    @Override
    public String toString() {
        return questions.toString()+System.lineSeparator();
    }
}
