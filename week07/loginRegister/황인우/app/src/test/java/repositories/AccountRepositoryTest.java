package repositories;

import models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountRepositoryTest {
  @Test
  void findAccount() {
    AccountRepository accountRepository = new AccountRepository();

    accountRepository.addAccount(
        new Account("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"));
    accountRepository.addAccount(
        new Account("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"));
    accountRepository.addAccount(
        new Account("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com"));

    Account account = accountRepository.findAccount("hsjkdss228");

    assertEquals("황인우", account.name());
    assertEquals("hsjkdss228", account.id());
    assertEquals("dlsdn12", account.password());
    assertEquals("hsjkdss228@naver.com", account.email());
  }

  @Test
  void addAccount() {
    AccountRepository accountRepository = new AccountRepository();

    accountRepository.addAccount(
        new Account("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"));
    accountRepository.addAccount(
        new Account("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"));
    accountRepository.addAccount(
        new Account("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com"));

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
