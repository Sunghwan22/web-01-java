package com.makao.Bank.services;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest {
  @Test
  void findByIdentity() {
    AccountRepository acccountRepository = new AccountRepository();

    AccountService accountService = new AccountService(acccountRepository);

    Account account = accountService.myAccount();

    assertThat(account.identifier()).isEqualTo("1234");
  }

}