package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@TestComponent
class QuizTest {

    private static List<Question> questions = new ArrayList<>();

    @BeforeAll
    static void setUpBeforeAll() {
        questions.add(new Question(2, "text1", 3));
        questions.add(new Question(1, "text2", 1));
        questions.add(new Question(3, "text3", 2));

    }

    @Test
    @DisplayName("should create sorted test by questions ids")
    void create() {
        Quiz quiz = Quiz.createSortedQuiz(questions);
        assertThat(quiz.getQuestions()).isSortedAccordingTo(Question::compareTo);
    }
}