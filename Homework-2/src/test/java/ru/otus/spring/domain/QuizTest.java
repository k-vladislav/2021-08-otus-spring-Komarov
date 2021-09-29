package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class QuizTest {

    private static List<Question> questions = new ArrayList<>();

    @BeforeAll
    static void setUpBeforeAll() {
        questions.add(new Question("text1",2,3));
        questions.add(new Question("text2",1,1));
        questions.add(new Question("text3",3,2));

    }

    @Test
    void create() {
        Quiz quiz = Quiz.create(questions);
        assertThat(quiz.getQuestions()).isSortedAccordingTo(Question::compareTo);
    }

    @Test
    void getQuestions() {
    }

    @Test
    void testToString() {
    }
}