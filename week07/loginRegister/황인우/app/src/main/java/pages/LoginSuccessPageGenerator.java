package pages;

import models.User;

public class LoginSuccessPageGenerator extends PageGenerator {
  private User user;

  public LoginSuccessPageGenerator(User user) {
    super();
    this.user = user;
  }

  @Override
  public String content() {
    return "<h2>마카오 뱅크</h2>\n" +
        "<p>안녕하세요 " + user.name() + " 님, 행복을 적립하는 마카오뱅크입니다.</p>\n";
  }
}
