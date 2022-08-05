package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  @Test
  void creation() {
    User user = new User("조성환","tidls45","1234","tidls45@");

    assertEquals("조성환", user.name());
    assertEquals("tidls45", user.id());
    assertEquals("1234", user.password());
    assertEquals("tidls45@", user.email());
  }

  @Test
  void toCsvRow() {
    User user1 = new User("조성환","tidls45","1234","tidls45@");
    User user2 = new User("엔드류 테이트","q1w2e3","1234","tidls45@");

    assertEquals("조성환,tidls45,1234,tidls45@", user1.toCsvRow());
    assertEquals("엔드류 테이트,q1w2e3,1234,tidls45@", user2.toCsvRow());
  }
}
