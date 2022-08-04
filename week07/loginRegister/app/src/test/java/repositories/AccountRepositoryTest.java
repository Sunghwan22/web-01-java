package repositories;

import models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {
  @Test
  void find() {
    AccountRepository accountRepository = new AccountRepository();

    Account account = new Account("조성환","tidls45","1234","tidls45@");

    accountRepository.accounts().put("tidls45", account);

    assertEquals(account, accountRepository.find("tidls45"));
  }

}