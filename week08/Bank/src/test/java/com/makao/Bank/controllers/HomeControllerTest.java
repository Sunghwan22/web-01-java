package com.makao.Bank.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void html() {
    HomeController homeController = new HomeController();

    String html = homeController.home();
  }

  @Test
  void mockMvcHome() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("Hello, world!")
        ));
  }
}
