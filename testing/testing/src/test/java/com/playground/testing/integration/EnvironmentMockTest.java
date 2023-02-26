package com.playground.testing.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.*;

//@AutoConfigureWebTestClient
@AutoConfigureMockMvc
@SpringBootTest //(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // MOCK은 default
public class EnvironmentMockTest {

    @Autowired
    MockMvc mockMvc;

//    @Autowired
//    WebTestClient webTestClient; // web-flux 사용할 때 가능 (MOCK, RANDOM_PORT 둘다 가능)

    @Test
    @DisplayName("SpringBooTest(MOCK) @AutoConfigureMockMvc -> MockMvc 자동 주입")
    public void injectMockMvc() {
        // MockMvc은 @AutoConfigureMockMvc 붙여줘야 자동 주입된다.
        assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("SpringBooTest(MOCK) MockMvc, REST API Test")
    public void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Service Controller"))
                .andDo(MockMvcResultHandlers.print());
    }
}
