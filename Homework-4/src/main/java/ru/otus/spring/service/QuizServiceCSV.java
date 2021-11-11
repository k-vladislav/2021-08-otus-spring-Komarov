package ru.otus.spring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuizDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Service
public class QuizServiceCSV implements QuizService<CSVRecord> {
    private final QuizDao dao;

    public QuizServiceCSV(QuizDao dao) {
        this.dao = dao;
    }

    @Override
    @Bean
    public List<CSVRecord> getQuizRawData() {
        InputStream quizIS = dao.getQuiz();
        return getQuizCSVRecords(quizIS);
    }

    private List<CSVRecord> getQuizCSVRecords(InputStream quizIS) {
        BufferedReader br = new BufferedReader(new InputStreamReader(quizIS));
        try (CSVParser csvRecords = CSVParser.parse(br, CSVFormat.EXCEL.builder().setDelimiter(';').setHeader().build())) {
            return csvRecords.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
