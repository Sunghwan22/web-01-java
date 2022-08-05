package services;

import models.User;
import repositories.UserRepository;

import java.util.Map;

public class LoginService {
  public static final String BLANK_LOGIN_FORM = "BLANK_LOGIN_FORM";
  public static final String ID_NOT_FOUND = "ID_NOT_FOUND";
  public static final String WRONG_PASSWORD = "WRONG_PASSWORD";
  public static final String SUCCESS = "SUCCESS";

  private UserRepository userRepository;

  public LoginService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String LoginFormCheck(Map<String, String> formData) {
    User findUser = userRepository.find(formData.get("id"));

    if (!formData.containsKey("id") ||
        !formData.containsKey("password")) {
      return LoginService.BLANK_LOGIN_FORM;
    }

    if (findUser == null) {
      return LoginService.ID_NOT_FOUND;
    }

    if (!findUser.password().equals(formData.get("password"))) {
      return LoginService.WRONG_PASSWORD;
    }

    return LoginService.SUCCESS;
  }
}
