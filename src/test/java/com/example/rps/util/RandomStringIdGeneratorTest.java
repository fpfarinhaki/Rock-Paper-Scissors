package com.example.rps.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.Serializable;

public class RandomStringIdGeneratorTest {

    @Test
    public void shouldGenerateRandomStringId() {
        IdGenerator<String> randomIdGenerator = new RandomStringIdGenerator();

        String firstId = randomIdGenerator.generateId();
        String secondId = randomIdGenerator.generateId();

        Assertions.assertThat(firstId).isInstanceOf(Serializable.class);
        Assertions.assertThat(secondId).isInstanceOf(String.class);
        Assertions.assertThat(firstId).isNotEqualTo(secondId);
    }
}