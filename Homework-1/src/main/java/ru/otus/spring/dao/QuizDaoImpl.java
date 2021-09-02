package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class QuizDaoImpl implements QuizDao {
    private final String sourcePath;

    public QuizDaoImpl(String sourcePath) {
        if (sourcePath == null) throw new NullPointerException();
        this.sourcePath = sourcePath;
    }

    @Override
    public File getQuizFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(sourcePath);
        return resource.getFile();
    }
}

