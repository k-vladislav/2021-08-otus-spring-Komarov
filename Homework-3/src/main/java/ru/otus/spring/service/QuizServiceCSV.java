package ru.otus.spring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuizDaoCSV;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceCSV implements QuizService {
    private final QuizDaoCSV dao;
    private List<CSVRecord> csvRecords;

    public QuizServiceCSV(QuizDaoCSV dao) {
        this.dao = dao;
    }

    @Override
    public Optional<List<CSVRecord>> getQuizRawData() {
        InputStream quizIS = dao.getQuiz();
        return getQuizCSVRecords(quizIS);
    }

    private Optional<List<CSVRecord>> getQuizCSVRecords(InputStream quizIS) {
        BufferedReader br = new BufferedReader(new InputStreamReader(quizIS));
        try (CSVParser csvRecords = CSVParser.parse(br, CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().build())) {
            return Optional.of(csvRecords.getRecords());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
