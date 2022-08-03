package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
  @Test
  void creation() {
    Account account = new Account("이름", "아이디", "abcd1234", "abcd@naver.com");

    assertEquals("이름", account.name());
    assertEquals("아이디", account.id());
    assertEquals("abcd1234", account.password());
    assertEquals("abcd@naver.com", account.mail());
  }
}