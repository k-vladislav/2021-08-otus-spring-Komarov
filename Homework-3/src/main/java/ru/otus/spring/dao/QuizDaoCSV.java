package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
public class QuizDaoCSV implements QuizDao {

    private final String sourcePath;


    public QuizDaoCSV(@Qualifier("getPath") String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public InputStream getQuiz() {
        return QuizDaoCSV.class.getResourceAsStream(sourcePath);
    }

}

