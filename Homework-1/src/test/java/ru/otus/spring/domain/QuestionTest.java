package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    @DisplayName("stores String question in class field")
    void shouldHaveQuestion(){
        Question question = new Question("Question Test Title");
        assertEquals("Question Test Title",question.getQuestion());
    }

    @Test
    @DisplayName("increments answers counter when adding answers")
    void getAnswersCount() {
        Question question = new Question("Question Test Title");
        question.addAnswer(new Answer("Answer Option 1"));
        question.addAnswer(new Answer("Answer Option 2"));
        question.addAnswer(new Answer("Answer Option 3"));

        assertEquals(3,question.getAnswersCount());
    }


}