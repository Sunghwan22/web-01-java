package services;

import repositories.UserRepository;
import utils.LoginFormChecker;
import utils.RegistrationFormChecker;

import java.util.Map;

public class UserService {
  private UserRepository userRepository;
  private RegistrationFormChecker registrationFormChecker;
  private LoginFormChecker loginFormChecker;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

    registrationFormChecker = new RegistrationFormChecker(userRepository);
    loginFormChecker = new LoginFormChecker(userRepository);
  }

  public String checkRegistrationForm(Map<String, String> formData) {
    return registrationFormChecker.check(formData);
  }

  public String checkLoginForm(Map<String, String> formData) {
    return loginFormChecker.check(formData);
  }
}
