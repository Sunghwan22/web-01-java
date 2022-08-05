package utils;

import models.Account;
import repositories.AccountRepository;

import java.util.Map;

public class LoginFormChecker {
  public static final String INSUFFICIENT_LOGIN_INPUTS
      = "INSUFFICIENT_LOGIN_INPUTS";
  public static final String NOT_EXISTING_ID
      = "NOT_EXISTING_ID";
  public static final String INCORRECT_PASSWORD
      = "INCORRECT_PASSWORD";
  public static final String ACCEPTED
      = "ACCEPTED";

  private AccountRepository accountRepository;

  public LoginFormChecker(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public String check(Map<String, String> formData) {
    if (!formData.containsKey("id")
        || !formData.containsKey("password")) {
      return LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS;
    }

    Account found = accountRepository.findAccount(formData.get("id"));

    if (found == null) {
      return LoginFormChecker.NOT_EXISTING_ID;
    }

    if (!formData.get("password").equals(found.password())) {
      return LoginFormChecker.INCORRECT_PASSWORD;
    }

    return LoginFormChecker.ACCEPTED;
  }
}
