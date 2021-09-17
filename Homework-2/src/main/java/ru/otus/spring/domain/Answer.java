package ru.otus.spring.domain;

public class Answer {
    private String answer;
    private int questionId;
    private int answerId;

    public Answer(String answer) {
        this.answer = answer;
    }

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

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "    " + answerId + ". " + answer;
    }
}
