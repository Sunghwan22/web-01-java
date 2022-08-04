package utils;

import models.Account;

import java.util.Map;

public class RegistrationFormChecker {
  public static final String INSUFFICIENT = "INSUFFICIENT";
  public static final String ALREADY_EXISTING_ID = "ALREADY_EXISTING_ID";
  public static final String NOT_EQUAL_TO_PASSWORD_CHECK = "NOT_EQUAL_TO_PASSWORD_CHECK";
  public static final String ACCEPTED = "ACCEPTED";

  private Map<String, Account> accounts;

  public RegistrationFormChecker(Map<String, Account> accounts) {
    this.accounts = accounts;
  }

  public String check(Map<String, String> formData) {
    if (!formData.containsKey("name")
        || !formData.containsKey("id")
        || !formData.containsKey("password")
        || !formData.containsKey("password-check")
        || !formData.containsKey("email")) {
      return RegistrationFormChecker.INSUFFICIENT;
    }

    if (accounts.containsKey(formData.get("id"))) {
      return RegistrationFormChecker.ALREADY_EXISTING_ID;
    }

    if (!formData.get("password").equals(formData.get("password-check"))) {
      return RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK;
    }

    return RegistrationFormChecker.ACCEPTED;
  }
}
