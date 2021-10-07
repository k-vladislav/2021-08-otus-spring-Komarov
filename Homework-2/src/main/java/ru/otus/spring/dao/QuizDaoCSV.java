package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@PropertySource(value = "classpath:HW2.properties")
public class QuizDaoCSV implements QuizDao {
    private final String sourcePath;

    public QuizDaoCSV(@Value("${quiz.path}") String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public InputStream getQuiz() {
        return QuizDaoCSV.class.getResourceAsStream(sourcePath);
    }

}

