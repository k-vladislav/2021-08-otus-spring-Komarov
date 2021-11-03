package ru.otus.spring.domain;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.TestComponent;

import static org.junit.jupiter.api.Assertions.*;

@TestComponent
class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer("Answer Text", 3, 5);
    }


    @Test
    @DisplayName("should get correct QuestionId")
    void shouldGetCorrectQuestionId() {
       assertEquals(3,answer.getQuestionId());
    }

    @Test
    @DisplayName("should get correct AnswerId")
    void getAnswerId() {
        assertEquals(5,answer.getAnswerId());
    }

    @Test
    void compareTo() {
        Answer answerOne = new Answer("text",3,7);
        assertTrue(answer.compareTo(answerOne)<0, "Answer is lower than answerOne");
    }

    @Test
    void testToString() {
        String s = answer.toString();
        assertTrue(s.contains(String.valueOf(answer.getAnswerId())));
    }
}