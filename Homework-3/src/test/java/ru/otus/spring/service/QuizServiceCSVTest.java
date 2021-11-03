package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;
import ru.otus.spring.dao.QuizDaoCSV;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestComponent
class QuizServiceCSVTest {

    @Test
    void shouldCatchException() {
        String sourcePath = "/wrong/path";
        QuizDaoCSV dao = new QuizDaoCSV(sourcePath);
        QuizService<CSVRecord> mockQuizService = new QuizServiceCSV(dao);
        assertThrows(Exception.class, mockQuizService::getQuizRawData);
    }
}