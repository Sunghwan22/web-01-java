package services;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;
import utils.LoginFormChecker;
import utils.RegistrationFormChecker;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
  private UserRepository userRepository;

  @BeforeEach
  void initialization() {
    userRepository = new UserRepository();

    userRepository.addUser(
        new User("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"));
    userRepository.addUser(
        new User("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"));
    userRepository.addUser(
        new User("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com"));
  }

  @Test
  void checkNormalRegistrationForm() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.ACCEPTED, status);
  }

  @Test
  void checkEmptyNameRegistrationForm() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS, status);
  }

  @Test
  void checkEmptyPasswordRegistrationForm() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS, status);
  }

  @Test
  void checkEmptyPasswordCheckRegistrationForm() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS, status);
  }

  @Test
  void checkEmptyEmailRegistrationForm() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS, status);
  }

  @Test
  void alreadyExistingId() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "hsjkdss228");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "ivegotyouinmysights");
    formData.put("email", "sergeant@overwatch.com");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.ALREADY_EXISTING_ID, status);
  }

  @Test
  void notEqualToPasswordCheck() {
    UserService userService = new UserService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "솔저76");
    formData.put("id", "soldier76");
    formData.put("password", "ivegotyouinmysights");
    formData.put("password-check", "justicerainsfromabove");
    formData.put("email", "sergeant@overwatch.com");

    String status = userService.checkRegistrationForm(formData);

    assertEquals(RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK, status);
  }

  @Test
  void checkNormalLoginForm() {
    LoginFormChecker loginFormChecker
        = new LoginFormChecker(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "hsjkdss228");
    formData.put("password", "dlsdn12");

    String status = loginFormChecker.check(formData);

    assertEquals(LoginFormChecker.ACCEPTED, status);
  }

  @Test
  void checkEmptyIdLoginForm() {
    LoginFormChecker loginFormChecker
        = new LoginFormChecker(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("password", "dlsdn12");

    String status = loginFormChecker.check(formData);

    assertEquals(LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS, status);
  }

  @Test
  void checkEmptyPasswordLoginForm() {
    LoginFormChecker loginFormChecker
        = new LoginFormChecker(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "hsjkdss228");

    String status = loginFormChecker.check(formData);

    assertEquals(LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS, status);
  }

  @Test
  void notExistingId() {
    LoginFormChecker loginFormChecker
        = new LoginFormChecker(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "chikorita");
    formData.put("password", "dlsdn12");

    String status = loginFormChecker.check(formData);

    assertEquals(LoginFormChecker.NOT_EXISTING_ID, status);
  }

  @Test
  void incorrectPassword() {
    LoginFormChecker loginFormChecker
        = new LoginFormChecker(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "hsjkdss228");
    formData.put("password", "hwaseonggazua");

    String status = loginFormChecker.check(formData);

    assertEquals(LoginFormChecker.INCORRECT_PASSWORD, status);
  }
}
