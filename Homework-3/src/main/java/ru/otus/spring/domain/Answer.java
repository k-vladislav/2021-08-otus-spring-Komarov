package ru.otus.spring.domain;

public class Answer implements Comparable<Answer> {
    private final String answer;
    private final int questionId;
    private final int answerId;

    public Answer(int answerId, String answer, int questionId) {
        this.answer = answer;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    @Override
    public String toString() {
        return "    " + answerId + ". " + answer;
    }

    @Override
    public int compareTo(Answer o) {
        return this.answerId - o.answerId;
    }
}
