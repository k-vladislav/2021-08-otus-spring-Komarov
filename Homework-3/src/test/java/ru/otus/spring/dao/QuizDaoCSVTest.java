package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestComponent
class QuizDaoCSVTest {
   private QuizDaoCSV quizDaoCSV;

    @BeforeEach
    void setUp() {
        quizDaoCSV = new QuizDaoCSV("/Quizzes/quizTest.csv");
    }


    @Test
    void shouldGetNotNullInputStream() {
        InputStream quiz = quizDaoCSV.getQuiz();
        assertNotNull(quiz);
    }
}