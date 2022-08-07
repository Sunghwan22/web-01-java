package com.makao.Bank.controllers;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import com.makao.Bank.services.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TransferController.class)
class TransferControllerTest {
  private static final String ACCOUNT_IDENTIFIER = "1234";
  private static final long INITIAL_AMOUNT = 3000;

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private AccountRepository accountRepository;

  @SpyBean
  private TransferService transferService;

  @BeforeEach
  void resetAccounts() {
    accountRepository.reset();
  }

  @Test
  void transferPage() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/transfer"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("받는 사람 계좌:")
        ));
  }

  @Test
  void transfer1000() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/transfer")
            .param("to", "2345")
            .param("amount", "1000"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("계좌 이체 성공!")
        ));

    Account account = accountRepository.find(ACCOUNT_IDENTIFIER);
    assertThat(account.amount()).isEqualTo(2000);

  }

  @Test
  void transfer500() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/transfer")
            .param("to", "2345")
            .param("amount", "500"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("계좌 이체 성공!")
        ));

    Account account = accountRepository.find(ACCOUNT_IDENTIFIER);
    assertThat(account.amount()).isEqualTo(2500);
  }

  @Test
  void transferWithWrongReceiver() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/transfer")
            .param("to", "-1")
            .param("amount", "1000"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("계좌 번호를 확인해 주세요!")
        ));


    Account account = accountRepository.find(ACCOUNT_IDENTIFIER);
    assertThat(account.amount()).isEqualTo(INITIAL_AMOUNT);
  }
}
