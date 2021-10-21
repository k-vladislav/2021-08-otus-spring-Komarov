package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Optional<List<CSVRecord>> getQuizRawData();
}
