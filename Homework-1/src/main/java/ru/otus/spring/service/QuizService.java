package ru.otus.spring.service;

import ru.otus.spring.domain.Quiz;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface QuizService {
    Quiz getQuiz() throws IOException;
    Quiz getQuizAsInputStream() throws IOException;
}
