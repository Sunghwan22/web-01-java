package utils;

import models.User;
import repositories.UserRepository;

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

  private UserRepository userRepository;

  public LoginFormChecker(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String check(Map<String, String> formData) {
    if (!formData.containsKey("id")
        || !formData.containsKey("password")) {
      return LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS;
    }

    User found = userRepository.findUser(formData.get("id"));

    if (found == null) {
      return LoginFormChecker.NOT_EXISTING_ID;
    }

    if (!formData.get("password").equals(found.password())) {
      return LoginFormChecker.INCORRECT_PASSWORD;
    }

    return LoginFormChecker.ACCEPTED;
  }
}
