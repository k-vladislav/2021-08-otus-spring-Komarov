package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    @Test
    void create() {
        InputStream sysInBackup = System.in;
        String fNamelName = "Ivan " + System.lineSeparator() + "Ivanov";
        ByteArrayInputStream in = new ByteArrayInputStream(fNamelName.getBytes());
        System.setIn(in);
        user = User.create();
        System.setIn(sysInBackup);
    }

    @Test
    void testToString() {
        assertThat(user).isNotNull();
        assertThat(user.toString()).contains("Ivan").contains("Ivanov");
    }
}
