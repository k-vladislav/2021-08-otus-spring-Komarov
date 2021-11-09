package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizBuilderCSV implements QuizBuilder<CSVRecord> {
    private final String localeLabel;

    public QuizBuilderCSV(@Qualifier("getLocaleLabel") String localeLabel) {
        this.localeLabel = localeLabel;
    }

    @Bean
    @Override
    public Quiz buildQuiz(List<CSVRecord> csvRecords) {
        List<Question> questions = readQnA(csvRecords);
        return Quiz.createSortedQuiz(questions);
    }

    private List<Question> readQnA(List<CSVRecord> csvRecords) {
        if (csvRecords.isEmpty()) return Collections.emptyList();
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        String val = "RU".equalsIgnoreCase(localeLabel) ? "VALUERU" : "VALUE";
        for (CSVRecord csvRecord : csvRecords) {
            String type = csvRecord.get("TYPE");
            int qID = Integer.parseInt(csvRecord.get("QID"));
            int aID = Integer.parseInt(csvRecord.get("AID"));
            String value = csvRecord.get(val);
            switch (type.trim()) {
                case "Q":
                    questions.add(new Question(qID, value, aID));
                    break;
                case "A":
                    answers.add(new Answer(aID, value, qID));
                    break;
            }
        }
        return matchQnA(questions, answers);
    }

    private List<Question> matchQnA(List<Question> questions, List<Answer> answers) {
        for (Question question : questions) {
            for (Answer answer : answers) {
                if (question.getQuestionId() == answer.getQuestionId()) {
                    question.addAnswer(answer);
                }
            }
        }
        return questions;
    }
}
