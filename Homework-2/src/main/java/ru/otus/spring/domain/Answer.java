package ru.otus.spring.domain;

public class Answer {
    private final String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return answer;
    }
}
