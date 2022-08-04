package utils;

import models.Account;
import repositories.AccountRepository;

import java.util.Map;

public class AccountValidityChecker {
  public static final String INSUFFICIENT = "INSUFFICIENT";
  public static final String NOT_EXISTING_ID = "NOT_EXISTING_ID";
  public static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
  public static final String ACCEPTED = "ACCEPTED";

  private AccountRepository accountRepository;

  public AccountValidityChecker(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public String check(Map<String, String> formData) {
    if (!formData.containsKey("id")
        || !formData.containsKey("password")) {
      return AccountValidityChecker.INSUFFICIENT;
    }

    Account found = accountRepository.findAccount(formData.get("id"));

    if (found == null) {
      return AccountValidityChecker.NOT_EXISTING_ID;
    }

    if (!formData.get("password").equals(found.password())) {
      return AccountValidityChecker.INCORRECT_PASSWORD;
    }

    return AccountValidityChecker.ACCEPTED;
  }
}
