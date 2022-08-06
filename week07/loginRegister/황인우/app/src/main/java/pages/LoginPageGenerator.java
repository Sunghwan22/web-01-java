package pages;

public class LoginPageGenerator extends PageGenerator {
  @Override
  public String content() {
    return "<h2>로그인</h2>" +
        "<form method=\"POST\" enctype=\"text/plain\">" +
        "<p>" +
        "<label>아이디 <input type=\"text\" name=\"id\" /></label>" +
        "</p>" +
        "<p>" +
        "<label>비밀번호 <input type=\"password\" name=\"password\" /></label>" +
        "</p>" +
        "<button type=\"submit\">로그인</button>" +
        "</form>";
  }
}
