package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


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