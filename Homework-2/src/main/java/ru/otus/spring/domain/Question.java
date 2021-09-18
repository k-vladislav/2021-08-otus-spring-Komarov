package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable<Question> {
    private String questionValue;
    private final List<Answer> answers = new ArrayList<>();
    private final int questionId;
    private int correctAnswerId;
    private int answersCount;
    private boolean correctAnswerAdded = false;

    public void setQuestionValue(String questionValue) {
        this.questionValue = questionValue;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    /**
     * @param questionValue   The question itself
     * @param questionId      ID of question
     * @param correctAnswerId expected ID of correct answer
     */
    public Question(String questionValue, int questionId, int correctAnswerId) {
        this.questionValue = questionValue;
        this.questionId = questionId;
        this.correctAnswerId = correctAnswerId;
    }

    public boolean isCorrectAnswerAdded() {
        return correctAnswerAdded;
    }

    public void addAnswer(Answer newAnswer) {
        Answer matchedAnswer = answers.stream()
                .filter(answer -> newAnswer.getAnswerId() == answer.getAnswerId())
                .findFirst()
                .orElse(null);
        if (matchedAnswer != null) {
            matchedAnswer.setAnswer(newAnswer.getAnswerValue());
        } else {
            answers.add(newAnswer);
            answersCount++;
        }
        if (newAnswer.getAnswerId() == correctAnswerId) correctAnswerAdded = true;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    @Override
    public String toString() {
        return questionId + ". " + questionValue;
    }

    public void display() {
        System.out.println(this);
        for (Answer answer : this.answers) {
            System.out.println(answer);
        }
    }

    public String getQuestion() {
        return questionValue;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    @Override
    public int compareTo(Question o) {
        return this.questionId - o.questionId;
    }
}
