package ru.otus.spring.domain;

public class Answer implements Comparable<Answer> {
    private String answer;
    private final int questionId;
    private final int answerId;

    /**
     * @param answerValue Answer
     * @param questionId  Matching question ID
     * @param answerId    Answer ID
     */
    public Answer(String answerValue, int questionId, int answerId) {
        this.answer = answerValue;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public String getAnswerValue() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
