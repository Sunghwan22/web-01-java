package services;

import models.User;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
  @Test
  void blankInformation() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    LoginService loginService = new LoginService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "tidls");

    String status = loginService.LoginFormCheck(formData);

    assertEquals(LoginService.BLANK_LOGIN_FORM, status);
  }

  @Test
  void idNotFound() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    LoginService loginService = new LoginService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "tidls");
    formData.put("password", "1234");

    String status = loginService.LoginFormCheck(formData);

    assertEquals(LoginService.ID_NOT_FOUND, status);
  }

  @Test
  void wrongPassword() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    LoginService loginService = new LoginService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "tidls45");
    formData.put("password", "123");

    String status = loginService.LoginFormCheck(formData);

    assertEquals(LoginService.WRONG_PASSWORD, status);
  }

  @Test
  void success() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    LoginService loginService = new LoginService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("id", "tidls45");
    formData.put("password", "1234");

    String status = loginService.LoginFormCheck(formData);

    assertEquals(LoginService.SUCCESS, status);
  }
}
