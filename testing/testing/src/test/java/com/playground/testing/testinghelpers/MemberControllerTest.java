package com.playground.testing.testinghelpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    MemberMockMvcHelper helper;;

    @BeforeEach
    public void init() {
        helper = new MemberMockMvcHelper(mockMvc);
    }

    @Test
    @DisplayName("Fixture 없이 테스트")
    public void withFixture() throws Exception {
        SignupRequest dto = new SignupRequest("user", "user@gmail.com");
        String signupContent = new ObjectMapper().writeValueAsString(dto);

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signupContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Fixture와 re")
    public void withoutFixture() throws Exception {
        helper.회원가입을_한다(MemberFixture.BLOO)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}