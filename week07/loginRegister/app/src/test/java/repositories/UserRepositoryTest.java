package repositories;

import models.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
  @Test
  void find() throws FileNotFoundException {
    UserRepository userRepository = new UserRepository();

    User user = new User("조성환","tidls1234","1234","tidls45@");

    userRepository.users().put(user.id(), user);

    assertEquals(user, userRepository.find("tidls1234"));
  }
}
