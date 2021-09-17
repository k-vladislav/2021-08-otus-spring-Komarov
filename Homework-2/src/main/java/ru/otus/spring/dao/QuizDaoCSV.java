package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class QuizDaoCSV implements QuizDao {
    private final String sourcePath;


    public QuizDaoCSV(@Value("/Quizzes/quiz.csv")String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public InputStream loadQuiz() { //todo Class.class.getResourceAsStream / QuizDaoCSV.class.getClassLoader.getResourceAsAtream ?
        return QuizDaoCSV.class.getResourceAsStream(sourcePath);
    }
}

