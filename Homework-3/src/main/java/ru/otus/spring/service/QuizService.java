package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Optional<List<CSVRecord>> getQuizRawData();
}
