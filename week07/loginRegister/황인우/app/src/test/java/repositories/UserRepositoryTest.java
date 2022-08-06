package repositories;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
  @Test
  void findAccount() {
    UserRepository userRepository = new UserRepository();

    userRepository.addUser(
        new User("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"));
    userRepository.addUser(
        new User("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"));
    userRepository.addUser(
        new User("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com"));

    User user = userRepository.findUser("hsjkdss228");

    assertEquals("황인우", user.name());
    assertEquals("hsjkdss228", user.id());
    assertEquals("dlsdn12", user.password());
    assertEquals("hsjkdss228@naver.com", user.email());
  }

  @Test
  void addAccount() {
    UserRepository userRepository = new UserRepository();

    userRepository.addUser(
        new User("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"));
    userRepository.addUser(
        new User("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"));
    userRepository.addUser(
        new User("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com"));

    User user = new User(
        "솔저76", "soldier76", "ivegotyouinmysights", "sergeant@overwatch.com");

    userRepository.addUser(user);

    User found = userRepository.findUser("soldier76");

    assertEquals("솔저76", found.name());
    assertEquals("soldier76", found.id());
    assertEquals("ivegotyouinmysights", found.password());
    assertEquals("sergeant@overwatch.com", found.email());
  }
}
