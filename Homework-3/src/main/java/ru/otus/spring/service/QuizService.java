package ru.otus.spring.service;

import java.util.List;

public interface QuizService<T> {
    List<T> getQuizRawData();
}
