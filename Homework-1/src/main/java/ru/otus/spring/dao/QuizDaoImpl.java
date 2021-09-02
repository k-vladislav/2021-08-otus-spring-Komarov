package ru.otus.spring.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class QuizDaoImpl implements QuizDao {
    private final File quizFile;

    public QuizDaoImpl(String sourcePath) throws FileNotFoundException {
        URL resource = getClass().getResource(sourcePath);
        if (resource!=null) {
            quizFile=new File(resource.getPath());
        } else {
            throw new FileNotFoundException();
        }

    }

    @Override
    public File getQuizFile() {
        return quizFile;
    }
}

