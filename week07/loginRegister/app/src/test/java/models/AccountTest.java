package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
  @Test
  void creation() {
    Account account = new Account("조성환","tidls45","1234","tidls45@");

    assertEquals("조성환", account.name());
    assertEquals("tidls45", account.id());
    assertEquals("1234", account.password());
    assertEquals("tidls45@", account.email());
  }

  @Test
  void toCsvRow() {
    Account account1 = new Account("조성환","tidls45","1234","tidls45@");
    Account account2 = new Account("엔드류 테이트","q1w2e3","1234","tidls45@");

    assertEquals("조성환,tidls45,1234,tidls45@", account1.toCsvRow());
    assertEquals("엔드류 테이트,q1w2e3,1234,tidls45@", account2.toCsvRow());
  }
}
