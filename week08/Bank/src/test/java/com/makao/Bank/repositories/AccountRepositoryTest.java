package com.makao.Bank.repositories;

import com.makao.Bank.models.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest {
  @Test
  void find() {
    AccountRepository accountRepository = new AccountRepository();

    Account account = accountRepository.find("1234");

    assertThat(account.amount()).isEqualTo(3000);
  }

  @Test
  void NotFound() {
    AccountRepository accountRepository = new AccountRepository();

    Account account = accountRepository.find("-1");

    assertThat(account).isNull();
  }
}
