package ru.otus.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@TestComponent
@TestConfiguration
class UserTest {

    @Bean
    public User getMockUser() {
        return User.create("Ivan","Ivanov");
    }

    @Test
    void testToString() {
        User user = User.create("Ivan","Ivanov");
        assertThat(user).isNotNull();
        assertThat(user.toString()).contains("Ivan").contains("Ivanov");
    }
}
