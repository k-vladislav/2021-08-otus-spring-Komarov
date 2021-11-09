package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@TestComponent
class UserTest {

    @Test
    @DisplayName("should output firstname and lastname on output")
    void testToString() {
        User user = new User("Ivan", "Ivanov");
        assertThat(user).isNotNull();
        assertThat(user.toString()).contains("Ivan").contains("Ivanov");
    }
}
