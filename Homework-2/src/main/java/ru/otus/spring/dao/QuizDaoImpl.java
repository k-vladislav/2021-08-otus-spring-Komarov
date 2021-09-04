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
public class QuizDaoImpl implements QuizDao {
    private final Resource resource;


    public QuizDaoImpl(@Value("/Quizzes/Questions_Answers.csv")String sourcePath) {
        if (sourcePath == null || sourcePath.isBlank() || sourcePath.isEmpty()) throw new RuntimeException();
        resource = new ClassPathResource(sourcePath);
        if (!resource.exists() || !resource.isReadable()) throw new RuntimeException();
    }

    @Override
    public File getQuizFile() throws IOException {
        return resource.getFile();
    }

    @Override
    public InputStream getQuizInputStream() throws IOException {
        return resource.getInputStream();
    }
}

