package pages;

import models.Account;

public class LoginSuccessPageGenerator extends PageGenerator {
  private Account account;

  public LoginSuccessPageGenerator(Account account) {
    super();
    this.account = account;
  }

  @Override
  public String content() {
    return "<h2>마카오 뱅크</h2>\n" +
        "<p>안녕하세요 " + account.name() + " 님, 행복을 적립하는 마카오뱅크입니다.</p>\n";
  }
}
