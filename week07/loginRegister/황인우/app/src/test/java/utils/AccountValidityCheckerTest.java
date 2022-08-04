package utils;

import models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.AccountRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidityCheckerTest {
  // 오류 케이스
  // - 모든 데이터를 입력하지 않았을 경우
  // - 사용자 아이디가 존재하지 않을 경우
  // - 비밂번호가 틀렸을 경우
  private AccountRepository accountRepository;

  @BeforeEach
  void initialize() {
    accountRepository = new AccountRepository();

    accountRepository.addAccount(
        new Account("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"));
    accountRepository.addAccount(
        new Account("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"));
    accountRepository.addAccount(
        new Account("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com"));
  }

  @Test
  void checkNormalForm() {
    AccountValidityChecker accountValidityChecker
        = new AccountValidityChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "hsjkdss228");
    formData.put("password", "dlsdn12");

    String status = accountValidityChecker.check(formData);

    assertEquals(AccountValidityChecker.ACCEPTED, status);
  }

  @Test
  void checkEmptyIdForm() {
    AccountValidityChecker accountValidityChecker
        = new AccountValidityChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("password", "dlsdn12");

    String status = accountValidityChecker.check(formData);

    assertEquals(AccountValidityChecker.INSUFFICIENT, status);
  }

  @Test
  void checkEmptyPasswordForm() {
    AccountValidityChecker accountValidityChecker
        = new AccountValidityChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "hsjkdss228");

    String status = accountValidityChecker.check(formData);

    assertEquals(AccountValidityChecker.INSUFFICIENT, status);
  }

  @Test
  void notExistingId() {
    AccountValidityChecker accountValidityChecker
        = new AccountValidityChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "chikorita");
    formData.put("password", "dlsdn12");

    String status = accountValidityChecker.check(formData);

    assertEquals(AccountValidityChecker.NOT_EXISTING_ID, status);
  }

  @Test
  void incorrectPassword() {
    AccountValidityChecker accountValidityChecker
        = new AccountValidityChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "hsjkdss228");
    formData.put("password", "hwaseonggazua");

    String status = accountValidityChecker.check(formData);

    assertEquals(AccountValidityChecker.INCORRECT_PASSWORD, status);
  }
}
