package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable<Question> {
    private final String questionText;
    private final List<Answer> answers = new ArrayList<>();
    private final int questionId;
    private final int correctAnswerId;
    private boolean correctAnswerAdded = false;

    public Question(String questionValue, int questionId, int correctAnswerId) {
        this.questionText = questionValue;
        this.questionId = questionId;
        this.correctAnswerId = correctAnswerId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isCorrectAnswerAdded() {
        return correctAnswerAdded;
    }

    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
        if (newAnswer.getAnswerId() == correctAnswerId) correctAnswerAdded = true;
    }

    public int getAnswersCount() {
        return answers.size();
    }

    @Override
    public String toString() {
        return questionId + ". " + questionText;
    }

    public void display() {
        System.out.println(this);
        for (Answer answer : this.answers) {
            System.out.println(answer);
        }
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public boolean isCorrectAnswer(int answer) {
        return answer == correctAnswerId;
    }

    @Override
    public int compareTo(Question o) {
        return this.questionId - o.questionId;
    }
}
