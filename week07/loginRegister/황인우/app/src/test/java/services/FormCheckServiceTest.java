package services;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

import java.util.Map;

class FormCheckServiceTest {
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
  void loginFormCheck() {
    FormCheckService formCheckService = new FormCheckService(userRepository);

    Map<String, String> formData = Map.of(

    );
  }

  @Test
  void registrationFormCheck() {

  }
}
