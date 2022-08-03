package utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFormCheckerTest {
  @Test
  void check() {
    RegistrationFormChecker registrationFormChecker = new RegistrationFormChecker();

    Map<String, String> formData = new HashMap<>();
    formData.put("name", "김인우");
    formData.put("id", "hsjk");
    formData.put("password", "1234");
    formData.put("password-check", "1234");
    formData.put("email", "hsjk@naver.com");

    String status = registrationFormChecker.check(formData);

    assertEquals("ACCEPTED", status);
  }
}