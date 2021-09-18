package ru.otus.spring.dao;

import java.io.InputStream;

public class QuizDaoCSV implements QuizDao {
    private final String sourcePath;

    public QuizDaoCSV(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public InputStream loadQuiz() {
        return QuizDaoCSV.class.getResourceAsStream(sourcePath);
    }
}

