package utils;

import models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.AccountRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFormCheckerTest {
  // 오류 케이스
  // - 모든 데이터를 입력하지 않았을 경우
  // - 사용자 아이디가 이미 존재할 경우
  // - 비밂번호와 비밀번호 재확인이 다를 경우
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
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.ACCEPTED, status);
  }

  @Test
  void checkEmptyNameForm() {
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT, status);
  }

  @Test
  void checkEmptyPasswordForm() {
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT, status);
  }

  @Test
  void checkEmptyPasswordCheckForm() {
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT, status);
  }

  @Test
  void checkEmptyEmailForm() {
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT, status);
  }

  @Test
  void alreadyExistingId() {
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "hsjkdss228");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.ALREADY_EXISTING_ID, status);
  }

  @Test
  void notEqualToPasswordCheck() {
    RegistrationFormChecker registrationFormChecker
        = new RegistrationFormChecker(accountRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "justicerainsfromabove");
    formData.put("email", "sergeant@overwatch.com");

    String status = registrationFormChecker.check(formData);

    assertEquals(RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK, status);
  }
}
