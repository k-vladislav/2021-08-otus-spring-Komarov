package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.dao.QuizDao;

import java.io.InputStream;
import java.util.List;

import static org.mockito.Mockito.doReturn;


@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@TestComponent
class QuizServiceCSVTest {
    @Configuration
    static class QuizServiceTestConfiguration {

    }

    @MockBean
    QuizDao dao;

    @MockBean
    QuizService<CSVRecord> quizService;

    @MockBean
    InputStream inputStream;

    @Test
    @DisplayName("should return list of CSVRecord")
    void shouldReturnListOfCSVRecords() {
        doReturn(inputStream).when(dao).getQuiz();
        Assertions.assertThat(quizService.getQuizRawData()).isInstanceOf(List.class);
    }
}