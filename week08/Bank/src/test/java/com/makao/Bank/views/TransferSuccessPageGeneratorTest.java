package com.makao.Bank.views;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransferSuccessPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new TransferSuccessPageGenerator();

    String html = pageGenerator.content();

    assertThat(html).contains("계좌 이체 성공!");
  }

}