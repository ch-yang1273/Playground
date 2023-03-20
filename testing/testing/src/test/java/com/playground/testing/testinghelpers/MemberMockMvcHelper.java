package com.playground.testing.testinghelpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MemberMockMvcHelper {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    private final String SiGNUP_URL = "/signup";

    public MemberMockMvcHelper(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private String convertToContent(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultActions 회원가입을_한다(MemberFixture memberFixture) {
        SignupRequest request = new SignupRequest(memberFixture.getName(), memberFixture.getEmail());
        String content = convertToContent(request);
        try {
            return mockMvc.perform(MockMvcRequestBuilders.post(SiGNUP_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
