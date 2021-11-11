package ru.otus.spring.service;

import ru.otus.spring.domain.Quiz;

import java.util.List;

public interface QuizBuilder<T> {
    Quiz buildQuiz(List<T> rawList);
}
