package com.makao.Bank.views;

public abstract class PageGenerator {
  public String html() {
    return "<!DOCTYPE HTML>\n" +
        "<html>\n" +
        "<head>\n" +
        "<meta charset=\"UTF-8\">\n" +
        "<title>MakaoBank</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<nav>\n" +
        "<a href=\"/\">Home</a>   " +
        "<a href=\"/account\">잔액 조회</a>" +
        "<a href=\"/transfer\">계좌 이체</a>" +
        content() +
        "</body>\n" +
        "</html>";
  }

  public abstract String content();
}
