package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;

import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;


@SpringBootTest
@TestComponent
class QuizServiceCSVTest {
    @Configuration
     static class QuizServiceTestConfiguration {

    }

    @MockBean
    QuizService<CSVRecord> quizService;

    @MockBean
    List<CSVRecord> csvRecords;

    @Test
    void shouldReturnListOfCSVRecords() { //todo что я тут проверяю
        //Mockito.when(quizService.getQuizRawData()).thenReturn(Collections.emptyList());
        Mockito.when(quizService.getQuizRawData()).thenReturn(csvRecords);
        Assertions.assertThat(quizService.getQuizRawData()).isEqualTo(csvRecords);
    }

/*    @Test
    void shouldCatchException() {
        String sourcePath = "/wrong/path";
        QuizDaoCSV dao = new QuizDaoCSV(sourcePath);
        QuizService<CSVRecord> mockQuizService = new QuizServiceCSV(dao);
        assertThrows(Exception.class, mockQuizService::getQuizRawData);
    }*/
}