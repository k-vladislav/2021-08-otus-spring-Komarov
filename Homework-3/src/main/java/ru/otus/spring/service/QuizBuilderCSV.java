package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizBuilderCSV implements QuizBuilder {
    private List<Question> questions;
    private List<Answer> answers;
    private final List<CSVRecord> csvRecords;

    public QuizBuilderCSV(List<CSVRecord> csvRecords) {
        this.csvRecords = csvRecords;
    }

    @Bean
    @Override
    public Quiz buildQuiz() {
        readQnA();
        matchQnA();
        return Quiz.createSortedQuiz(questions);
    }

    private void readQnA() {
        if (csvRecords.isEmpty()) return;
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {
            String type = csvRecord.get("TYPE");
            int qID = Integer.parseInt(csvRecord.get("QID"));
            int aID = Integer.parseInt(csvRecord.get("AID"));
            String value = csvRecord.get("VALUE");
            switch (type.trim()) {
                case "Q":
                    questions.add(new Question(value, qID, aID));
                    break;
                case "A":
                    answers.add(new Answer(value, qID, aID));
                    break;
            }
        }
    }

    private void matchQnA() {
        for (Question question : questions) {
            for (Answer answer : answers) {
                if (question.getQuestionId() == answer.getQuestionId()) {
                    question.addAnswer(answer);
                }
            }
        }
    }
}
