package ru.otus.spring.domain;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@TestComponent
class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(3, "Answer Text", 5);
    }


    @Test
    @DisplayName("should get correct QuestionId")
    void shouldGetCorrectQuestionId() {
        assertEquals(5, answer.getQuestionId());
    }

    @Test
    @DisplayName("should get correct AnswerId")
    void getAnswerId() {
        assertEquals(3, answer.getAnswerId());
    }

    @Test
    @DisplayName("should be compared by answerId")
    void compareTo() {
        Answer answerOne = new Answer(5, "text", 7);
        assertTrue(answer.compareTo(answerOne) < 0, "Answer is lower than answerOne");
    }

    @Test
    @DisplayName("should contain AnswerId on println")
    void testToString() {
        String s = answer.toString();
        assertTrue(s.contains(String.valueOf(answer.getAnswerId())));
    }
}