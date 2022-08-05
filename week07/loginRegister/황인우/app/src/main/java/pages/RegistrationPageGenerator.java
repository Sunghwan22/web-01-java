package pages;

public class RegistrationPageGenerator extends PageGenerator {
  @Override
  public String content() {
    return "<h2>회원가입</h2>\n" +
        "<form method=\"POST\" enctype=\"text/plain\">\n" +
        "<p>\n" +
        "<label>이름 <input type=\"text\" name=\"name\" /></label>\n" +
        "</p>\n" +
        "<p>\n" +
        "<label>아이디 <input type=\"text\" name=\"id\" /></label>\n" +
        "</p>\n" +
        "<p>\n" +
        "<label>비밀번호 <input type=\"password\" name=\"password\" /></label>\n" +
        "</p>\n" +
        "<p>\n" +
        "<label>비밀번호 재확인 <input type=\"password\" name=\"password-check\" /></label>\n" +
        "</p>\n" +
        "<p>\n" +
        "<label>이메일 <input type=\"text\" name=\"email\" /></label>\n" +
        "</p>\n" +
        "<button type=\"submit\">회원가입</button>\n" +
        "</form>\n";
  }
}
