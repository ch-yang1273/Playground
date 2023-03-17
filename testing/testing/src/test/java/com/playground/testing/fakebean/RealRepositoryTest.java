package com.playground.testing.fakebean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RealRepositoryTest {

    @Autowired
    TargetRepository targetRepository;

    @Test
    public void realTest() {
        String key = "key";
        String value = "value";

        targetRepository.save(key, value);
        assertThat(targetRepository.get(key)).isEqualTo(value);
        targetRepository.delete(key);
        assertThat(targetRepository).isInstanceOf(RealRepository.class);
    }
}