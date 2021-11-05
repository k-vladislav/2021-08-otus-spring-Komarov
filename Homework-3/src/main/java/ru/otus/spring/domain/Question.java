package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable<Question> {
    private final String questionText;
    private final List<Answer> answers = new ArrayList<>();
    private final int questionId;
    private final int correctAnswerId;

    public Question(String questionValue, int questionId, int correctAnswerId) {
        this.questionText = questionValue;
        this.questionId = questionId;
        this.correctAnswerId = correctAnswerId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
    }

    public int getAnswersCount() {
        return answers.size();
    }

    @Override
    public String toString() {
        return questionId + ". " + questionText;
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
