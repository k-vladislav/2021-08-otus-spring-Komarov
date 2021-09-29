package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.QuizDaoCSV;
import ru.otus.spring.domain.Quiz;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceCSVTest {

    private QuizService quizService;

    @BeforeEach
    void setUp() {
        String sourcePath = "/Quizzes/quizTest.csv";
        QuizDaoCSV dao = new QuizDaoCSV(sourcePath);
        quizService = new QuizServiceCSV(dao);
    }

    @Test
    @DisplayName("should read QnA from CVS file")
    void shouldReadQnA() {
        Quiz quiz = quizService.getQuiz();
        assertEquals(5,quiz.getQuestions().size());
    }
}