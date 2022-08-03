package pages;

public class LoginPageGenerator extends PageGenerator {
  @Override
  public String content() {
    return "<h2>로그인</h2>" +
        "<form method=\"POST\" accept-charset=\"UTF-8\">" +
        "<p>" +
        "<label>아이디 </label>" +
        "<input type=\"text\" name=\"id\" />" +
        "</p>" +
        "<p>" +
        "<label>비밀번호 </label>" +
        "<input type=\"password\" name=\"password\" />" +
        "</p>" +
        "<button type=\"submit\">로그인</button>" +
        "</form>";
  }
}
