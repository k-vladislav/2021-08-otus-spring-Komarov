package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
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