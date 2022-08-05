package services;

import repositories.UserRepository;
import utils.RegistrationFormChecker;

import java.util.Map;

public class FormCheckService {
  private UserRepository userRepository;
  private RegistrationFormChecker registrationFormChecker;

  public FormCheckService(UserRepository userRepository) {
    this.userRepository = userRepository;

    registrationFormChecker = new RegistrationFormChecker(userRepository);
  }

  public String checkRegistrationForm(Map<String, String> formData) {
    return registrationFormChecker.check(formData);
  }
}
