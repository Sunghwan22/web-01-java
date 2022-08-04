package repositories;

import models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountRepositoryTest {
  @Test
  void findAccount() {
    AccountRepository accountRepository = new AccountRepository();

    Account account = accountRepository.findAccount("hsjkdss228");

    assertEquals("황인우", account.name());
    assertEquals("hsjkdss228", account.id());
    assertEquals("dlsdn12", account.password());
    assertEquals("hsjkdss228@naver.com", account.email());
  }

  @Test
  void addAccount() {
    AccountRepository accountRepository = new AccountRepository();

    Account account = new Account(
        "솔저76", "soldier76", "ivegotyouinmysights", "sergeant@overwatch.com");

    accountRepository.addAccount(account);

    Account found = accountRepository.findAccount("soldier76");

    assertEquals("솔저76", found.name());
    assertEquals("soldier76", found.id());
    assertEquals("ivegotyouinmysights", found.password());
    assertEquals("sergeant@overwatch.com", found.email());
  }
}
