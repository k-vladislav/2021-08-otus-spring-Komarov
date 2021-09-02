package ru.otus.spring.dao;

import ru.otus.spring.domain.Quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface QuizDao {
    File getQuizFile() throws IOException;
}
