package ru.otus.spring.dao;

import ru.otus.spring.domain.Quiz;

import java.io.File;
import java.io.FileNotFoundException;

public interface QuizDao {
    File getQuizFile();
}
