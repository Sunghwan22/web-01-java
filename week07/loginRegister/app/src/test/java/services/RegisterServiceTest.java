package services;

import models.User;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterServiceTest {
  @Test
  void registrationPass() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    userRepository.users().put(user.id(), user);

    RegisterService registerService = new RegisterService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "하이");
    formData.put("id", "tidls");
    formData.put("password", "ttqeqbgetg");
    formData.put("password-check", "ttqeqbgetg");
    formData.put("email", "sergeant@overwatch.com");

    String status = registerService.registrationFormCheck(formData);

    assertEquals(RegisterService.PASSED, status);
  }

  @Test
  void registrationWithoutInformation() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    userRepository.users().put(user.id(), user);

    RegisterService registerService = new RegisterService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "하이");
    formData.put("password", "ttqeqbgetg");
    formData.put("password-check", "ttqeqbgetg");
    formData.put("email", "sergeant@overwatch.com");

    String status = registerService.registrationFormCheck(formData);

    assertEquals(RegisterService.BLANK_REGISTER_FORM, status);
  }

  @Test
  void alreadyExistingUser() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    userRepository.users().put(user.id(), user);

    RegisterService registerService = new RegisterService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "조성환");
    formData.put("id", "tidls45");
    formData.put("password", "1234");
    formData.put("password-check", "1234");
    formData.put("email", "sergeant@overwatch.com");

    String status = registerService.registrationFormCheck(formData);

    assertEquals(RegisterService.ALREADY_EXISTING_USER, status);
  }

  @Test
  void passwordNotEqual() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환", "tidls45", "1234", "tidls45@naver.com");

    userRepository.users().put(user.id(), user);

    RegisterService registerService = new RegisterService(userRepository);

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "조성환");
    formData.put("id", "tidls79383");
    formData.put("password", "1234");
    formData.put("password-check", "2345");
    formData.put("email", "sergeant@overwatch.com");

    String status = registerService.registrationFormCheck(formData);

    assertEquals(RegisterService.NOT_EQUAL_PASSWORD, status);
  }
}
