package com.makao.Bank.views;

public class TransferPageGenerator extends PageGenerator {

  @Override
  public String content() {
    return "<form method = \"POST\">\n" +
        "<p>\n" +
        "받는 사람 계좌:\n" +
        "</p>\n" +
        "<input type = \"text\" name =\"to\">" +
        "<p>\n" +
        "보낼 금액:\n" +
        "</p>\n" +
        "<input type = \"number\" name =\"amount\">\n" +
        "<button type = \"submit\">확인</button>\n" +
        "</form>";
  }
}
