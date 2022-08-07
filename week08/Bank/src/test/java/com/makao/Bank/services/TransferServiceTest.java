package com.makao.Bank.services;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {
  @Test
  void transfer() {
    AccountRepository accountRepository = new AccountRepository();

    TransferService transferService = new TransferService(accountRepository);

    Account receiver = transferService.transfer("1234","2345",200);

    assertThat(receiver.amount()).isEqualTo(1000 + 200);
  }

  @Test
  void transferWithNotExsitedReceiver() {
    AccountRepository accountRepository = new AccountRepository();

    TransferService transferService = new TransferService(accountRepository);

    Account receiver = transferService.transfer("1234","456845678",200);

    assertThat(receiver).isNull();
  }
}