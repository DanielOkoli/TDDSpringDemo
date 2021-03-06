package com.example.tdddemo.controller;

import com.example.tdddemo.service.GreetingsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class GreetingsControllerTest  {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GreetingsService greetingsService;

    @Test
    public void maleGreetingTest200Ok() throws Exception {
        String name = "Daniel";
        String gender = "male";

        when(greetingsService.getGreetingsByGender("male"))
                .thenReturn("Mr.");

        mockMvc.perform(
                get("/api/greeting")
                .param("name", name)
                .param("gender", gender)

        ).andExpect(
                status().isOk()
        ).andExpect(
                 content().string(containsString(
                            String.format("Hello Mr. %s. How are you?", name)
                ))
        );

        verify(greetingsService).getGreetingsByGender(anyString());
    }

    @Test
    public void femaleGreetingTest200Ok() throws Exception {
        String name = "Danielle";
        String gender = "female";

        when(greetingsService.getGreetingsByGender("female"))
                .thenReturn("Mrs.");

        mockMvc.perform(
                get("/api/greeting")
                        .param("name", name)
                        .param("gender", gender)

        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(containsString(
                        String.format("Hello Mrs. %s. How are you?", name)
                ))
        );

    }

}
