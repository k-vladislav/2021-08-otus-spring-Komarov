package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Quiz {
    private final List<Question> questions;
    private final List<Answer> unmatchedAnswers;
    private int questionsCount;

    public List<Question> getQuestions() {
        return questions;
    }

    public Quiz() {
        questions = new ArrayList<>();
        unmatchedAnswers = new ArrayList<>();
        questionsCount = 0;
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
            unmatchedAnswers.add(newAnswer);
        }

    }

    public void addQuestion(Question newQuestion) {
        //todo sort Questions by QID
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
            questionsCount++;
        }
        //todo add answers from unmatchedAnswers
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void printQuiz() {
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            for (Answer answer : question.getAnswers()) {
                System.out.println("\t" + answer);
            }
        }
    }

    @Override
    public String toString() {
        return questions.toString() + System.lineSeparator();
    }
}
