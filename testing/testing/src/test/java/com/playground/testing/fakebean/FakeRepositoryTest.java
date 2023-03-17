package com.playground.testing.fakebean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(FakeRepository.class)
public class FakeRepositoryTest {

    @Autowired
    TargetRepository targetRepository;

    @Test
    public void fakeTest() {
        String key = "key";
        String value = "value";

        targetRepository.save(key, value);
        assertThat(targetRepository.get(key)).isEqualTo(value);
        targetRepository.delete(key);
        assertThat(targetRepository).isInstanceOf(FakeRepository.class);
    }
}
