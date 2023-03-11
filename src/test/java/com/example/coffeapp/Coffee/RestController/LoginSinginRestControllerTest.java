package com.example.coffeapp.Coffee.RestController;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class LoginSinginRestControllerTest {

    @Test
    void createAuthenticationToken() {
    }

    @Test
    void createUser() {
    }

    @Test
    void editUser() {
    }
//    public void aTest() throws Exception {
//        MvcResult result = mockMvc.perform(post("/api/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"username\":\"director\",\"password\":\"director\"}"))
//                .andReturn();
//
//        String response = result.getResponse().getContentAsString();
//        jwtToken = response.substring(7, response.length() - 1);
//
//        System.out.println("Bearer " + jwtToken);
//        System.out.println("response " + response);
//    }
}