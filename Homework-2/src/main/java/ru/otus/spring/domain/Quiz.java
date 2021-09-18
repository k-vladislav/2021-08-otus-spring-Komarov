package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final List<Question> questions;
    private final List<Answer> unmatchedAnswers;

    public List<Question> getQuestions() {
        return questions;
    }

    public void matchAnswers() {
        for (Answer unmatchedAnswer : unmatchedAnswers) {
            addAnswer(unmatchedAnswer);
        }
    }

    public Quiz() {
        questions = new ArrayList<>();
        unmatchedAnswers = new ArrayList<>();
    }

    public void addAnswer(Answer newAnswer) {
        int newAnswerQuestionId = newAnswer.getQuestionId();
        Question matchedQuestion = questions.stream()
                .filter(question -> newAnswerQuestionId == question.getQuestionId())
                .findFirst()
                .orElse(null);
        if (matchedQuestion != null) {
            matchedQuestion.addAnswer(newAnswer);
        } else {
            if (!unmatchedAnswers.contains(newAnswer)) unmatchedAnswers.add(newAnswer);
        }

    }

    public void addQuestion(Question newQuestion) {
        int currId, newId;
        boolean newQuestionReplacedExistingById = false;
        for (Question question : questions) {
            currId = question.getQuestionId();
            newId = newQuestion.getQuestionId();
            if (newId == currId) {
                question.setQuestionValue(newQuestion.getQuestion());
                question.setCorrectAnswerId(newQuestion.getCorrectAnswerId());
                newQuestionReplacedExistingById = true;
                break;
            }
        }
        if (!newQuestionReplacedExistingById) {
            questions.add(newQuestion);
        }
    }

    @Override
    public String toString() {
        return questions + System.lineSeparator();
    }

    public void sort() {
        questions.sort(Question::compareTo);
        for (Question question : questions) {
            question.getAnswers().sort(Answer::compareTo);
        }
    }
}
