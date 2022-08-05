package services;

import repositories.UserRepository;

import java.util.Map;

public class RegisterService {

  public static final String BLANK_REGISTER_FORM = "BLANK_REGISTER_FORM";
  public static final String NOT_EQUAL_PASSWORD = "NOT_EQUAL_PASSWORD";
  public static final String ALREADY_EXISTING_USER = "ALREADY_EXISTING_USER";
  public static final String PASSED = "PASSED";

  private UserRepository userRepository;

  public RegisterService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String registrationFormCheck(Map<String, String> formData) {
    if (!formData.containsKey("name") ||
        !formData.containsKey("id") ||
        !formData.containsKey("password") ||
        !formData.containsKey("password-check") ||
        !formData.containsKey("email")) {
      return RegisterService.BLANK_REGISTER_FORM;
    }

    if (userRepository.find(formData.get("id")) != null) {
      return RegisterService.ALREADY_EXISTING_USER;
    }

    if (!formData.get("password").equals(formData.get("password-check"))) {
      return RegisterService.NOT_EQUAL_PASSWORD;
    }
    return RegisterService.PASSED;
  }
}
