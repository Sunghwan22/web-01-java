package utils;

import repositories.UserRepository;

import java.util.Map;

public class RegistrationFormChecker {
  public static final String INSUFFICIENT_REGISTRATION_INPUTS
      = "INSUFFICIENT_REGISTRATION_INPUTS";
  public static final String ALREADY_EXISTING_ID = "ALREADY_EXISTING_ID";
  public static final String NOT_EQUAL_TO_PASSWORD_CHECK = "NOT_EQUAL_TO_PASSWORD_CHECK";
  public static final String ACCEPTED = "ACCEPTED";

  private UserRepository userRepository;

  public RegistrationFormChecker(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String check(Map<String, String> formData) {
    if (!formData.containsKey("name")
        || !formData.containsKey("id")
        || !formData.containsKey("password")
        || !formData.containsKey("password-check")
        || !formData.containsKey("email")) {
      return RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS;
    }

    if (userRepository.findUser(formData.get("id")) != null) {
      return RegistrationFormChecker.ALREADY_EXISTING_ID;
    }

    if (!formData.get("password").equals(formData.get("password-check"))) {
      return RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK;
    }

    return RegistrationFormChecker.ACCEPTED;
  }
}
