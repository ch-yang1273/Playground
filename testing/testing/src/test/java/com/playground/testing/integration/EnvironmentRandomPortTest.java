package com.playground.testing.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnvironmentRandomPortTest {

    @Autowired
    TestRestTemplate restTemplate; // MOCK 모드에서 사용 불가

    @LocalServerPort // MOCK에서 사용 불가
    int port;

    @Test
    @DisplayName("SpringBooTest(RANDOM_PORT) TestRestTemplate 자동 주입")
    public void injectTestRestTemplate() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    @DisplayName("SpringBooTest(RANDOM_PORT) @LocalServerPort")
    public void randomPort() {
        System.out.println(port);
        assertThat(port).isGreaterThan(0);
    }

    @Test
    @DisplayName("SpringBooTest(RANDOM_PORT) TestRestTemplate, REST API Test")
    public void hello() {
        String baseUrl = "http://localhost:" + port;
        String body = restTemplate.getForObject(baseUrl + "/hello", String.class);
        assertThat(body).isEqualTo("Service Controller");
    }
}
