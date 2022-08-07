package com.makao.Bank.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
  @Test
  void creation() {
    Account account=  new Account("1234","Ashal",3000);

    assertThat("1234").isEqualTo(account.identifier());
    assertThat("Ashal").isEqualTo(account.name());
    assertThat(3000).isEqualTo(account.amount());
  }

  @Test
  void transfer() {
    Account sender =  new Account("1234","Ashal",3000);
    Account receiver =  new Account("2345","JOKER",1000);

    int amount = 500;
    sender.transfer(receiver, amount);

    assertThat(sender.amount()).isEqualTo(3000 - amount);
    assertThat(receiver.amount()).isEqualTo(1000 + amount);
  }

  @Test
  void invalidTransfer() {
    Account sender =  new Account("1234","Ashal",3000);
    Account receiver =  new Account("2345","JOKER",1000);

    int amount = -500;
    sender.transfer(receiver, amount);

    assertThat(sender.amount()).isEqualTo(3000);
    assertThat(receiver.amount()).isEqualTo(1000);
  }
}