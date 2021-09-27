package ru.otus.spring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring.dao.QuizDaoCSV;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizServiceCSV implements QuizService {
    private final QuizDaoCSV dao;
    private List<Question> questions;
    private List<Answer> answers;

    public QuizServiceCSV(QuizDaoCSV dao) {
        this.dao = dao;
    }

    @Override
    public Quiz getQuiz() {
        readQnA();
        matchQnA();
        return Quiz.create(questions);
    }

    private void readQnA() {
        InputStream quizInputStream = dao.getQuiz();
        BufferedReader br = new BufferedReader(new InputStreamReader(quizInputStream));
        try (CSVParser csvRecords = CSVParser.parse(br, CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().build())) {
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
        } catch (IOException e) {
            e.printStackTrace();
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
