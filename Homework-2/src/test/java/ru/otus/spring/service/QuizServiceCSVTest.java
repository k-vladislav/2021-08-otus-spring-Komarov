package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuizDaoCSV;
import ru.otus.spring.domain.Quiz;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceCSVTest {

    private QuizService wrongQuizService;

    @BeforeEach
    void setUp() {
        String sourcePath = "/Quizzes/quizTest.csv";
        QuizDaoCSV dao = new QuizDaoCSV(sourcePath);
        wrongQuizService = new QuizServiceCSV(dao);
    }

    @Test
    @DisplayName("should read QnA from CVS file")
    void shouldReadQnA() {
        Quiz quiz = wrongQuizService.getQuiz();
        assertEquals(5,quiz.getQuestions().size());
    }

    @Test
    void shouldCatchIOException() {
        String sourcePath = "/wrong/path";
        QuizDaoCSV dao = new QuizDaoCSV(sourcePath);
        wrongQuizService = new QuizServiceCSV(dao);
        assertThrows(Exception.class,() -> wrongQuizService.getQuiz());
    }
}