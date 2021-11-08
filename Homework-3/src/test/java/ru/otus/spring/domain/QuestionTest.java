package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestComponent
class QuestionTest {
    Question question;

    @BeforeEach
    void setUp() {
        question = new Question(5, "text", 3);
    }

    @Test
    @DisplayName("should get correct QuestionId")
    void getQuestionId() {
        assertEquals(5, question.getQuestionId(), "Wrong QID");
    }

    @Test
    @DisplayName("should correctly add correct answer")
    void isCorrectAnswerAdded() {
        boolean isCorrectAnswerAdded = question.getAnswers().stream().map(Answer::getAnswerId).anyMatch(integer -> integer == question.getCorrectAnswerId());
        assertFalse(isCorrectAnswerAdded,"isCorrectAnswerAdded = true, but correct answer was not added");
        question.addAnswer(new Answer(3, "answer", 5));
        isCorrectAnswerAdded = question.getAnswers().stream().map(Answer::getAnswerId).anyMatch(integer -> integer == question.getCorrectAnswerId());
        assertTrue(isCorrectAnswerAdded,"isCorrectAnswerAdded = false, but correct answer was added");
    }

    @Test
    @DisplayName("should get list of answers")
    void getAnswers() {
        addAnswers();
        List<Answer> answers = question.getAnswers();
        assertThat(answers).hasSize(3);

    }

    @Test
    @DisplayName("should correctly add answers")
    void addAnswers() {
        question.addAnswer(new Answer(5, "answer 1", 1));
        question.addAnswer(new Answer(5, "answer 2", 2));
        question.addAnswer(new Answer(5, "answer 3", 3));
        assertEquals(3, question.getAnswers().size());
    }

    @Test
    @DisplayName("should correctly get count of answers")
    void getAnswersCount() {
        addAnswers();
        int answersCount = question.getAnswersCount();
        assertThat(answersCount).isEqualTo(3);
    }

    @Test
    @DisplayName("should print QuestionId and question text")
    void testToString() {
        String s = question.toString();
        assertThat(s).contains(String.valueOf(question.getQuestionId()))
                .containsPattern("[a-z]+");
    }

    @Test
    @DisplayName("should correctly get correct AnswerId")
    void getCorrectAnswerId() {
        assertThat(question.getCorrectAnswerId()).isEqualTo(3);
    }

    @Test
    @DisplayName("should correctly check if the answerId is correct")
    void isCorrectAnswer() {
        assertThat(question.isCorrectAnswer(1)).isFalse();
        assertThat(question.isCorrectAnswer(3)).isTrue();
    }

    @Test
    @DisplayName("should be compared by QuestionId")
    void compareTo() {
        Question questionToCompareWith = new Question(7, "some_text", 5);
        assertThat(question.compareTo(questionToCompareWith)).isLessThan(0);
    }
}