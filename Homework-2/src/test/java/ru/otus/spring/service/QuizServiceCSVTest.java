package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuizDaoCSV;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QuizServiceCSVTest {

    @Test
    void shouldCatchException() {
        String sourcePath = "/wrong/path";
        QuizDaoCSV dao = new QuizDaoCSV(sourcePath);
        QuizService mockQuizService = new QuizServiceCSV(dao);
        assertThrows(Exception.class, mockQuizService::getQuizRawData);
    }
}