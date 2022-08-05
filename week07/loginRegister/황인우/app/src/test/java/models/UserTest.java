package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  @Test
  void creation() {
    User user = new User("이름", "아이디", "abcd1234", "abcd@naver.com");

    assertEquals("이름", user.name());
    assertEquals("아이디", user.id());
    assertEquals("abcd1234", user.password());
    assertEquals("abcd@naver.com", user.email());
  }
}
