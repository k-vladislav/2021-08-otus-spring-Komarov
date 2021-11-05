package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@TestComponent
class QuestionTest {
Question question;
    @BeforeEach
    void setUp() {
        question=new Question("text",5,3);
    }

    @Test
    void getQuestionId() {
        assertEquals(5,question.getQuestionId(),"Wrong QID");
    }

    @Test
    void isCorrectAnswerAdded() {
       // assertFalse(question.isCorrectAnswerAdded());
        question.addAnswer(new Answer("answer",5,3));
       // assertTrue(question.isCorrectAnswerAdded());
    }

    @Test
    void getAnswers() {
        addAnswers();
        List<Answer> answers = question.getAnswers();
        assertThat(answers).hasSize(3);

    }

    @Test
    void addAnswers() {
        question.addAnswer(new Answer("answer 1",5,1));
        question.addAnswer(new Answer("answer 2",5,2));
        question.addAnswer(new Answer("answer 3",5,3));
        assertEquals(3,question.getAnswers().size());
    }

    @Test
    void getAnswersCount() {
        addAnswers();
        int answersCount = question.getAnswersCount();
        assertThat(answersCount).isEqualTo(3);
    }

    @Test
    void testToString() {
        String s = question.toString();
        assertThat(s).contains(String.valueOf(question.getQuestionId()))
                .containsPattern("[a-z]+");
    }

    @Test
    void display() {
        addAnswers();
        question.display();
    }

    @Test
    void getCorrectAnswerId() {
        assertThat(question.getCorrectAnswerId()).isEqualTo(3);
    }

    @Test
    void isCorrectAnswer() {
        assertThat(question.isCorrectAnswer(1)).isFalse();
        assertThat(question.isCorrectAnswer(3)).isTrue();
    }

    @Test
    void compareTo() {
        Question questionToCompareWith = new Question("some_text",7,5);
        assertThat(question.compareTo(questionToCompareWith)).isLessThan(0);
    }
}