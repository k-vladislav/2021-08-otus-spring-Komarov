package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable<Question> {
    private final int questionId;
    private final String questionText;
    private final int correctAnswerId;
    private final List<Answer> answers = new ArrayList<>();

    public Question(int questionId, String questionValue, int correctAnswerId) {
        this.questionId = questionId;
        this.questionText = questionValue;
        this.correctAnswerId = correctAnswerId;
    }

    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getAnswersCount() {
        return answers.size();
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
    public String toString() {
        return questionId + ". " + questionText;
    }

    @Override
    public int compareTo(Question o) {
        return this.questionId - o.questionId;
    }
}
