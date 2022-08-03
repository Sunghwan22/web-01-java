package pages;

public class RegisterPageGenerator extends PageGenerator {
  @Override
  public String content() { // 인코딩이 안됨
    return "<h1>회원가입</h1>\n" +
        "<form method=\"POST\"\" enctype=\"text/plain\">\n" +
        "<p>이름</p>\n" +
        "<input type=\"text\" name=\"name\" />\n " +
        "<p>아이디</p>\n" +
        "<input type=\"text\" name=\"id\" />\n " +
        "<p>비밀번호</p>\n" +
        "<input type=\"password\" name=\"password\" />\n " +
        "<p>비밀번호 재확인</p>\n" +
        "<input type=\"password\" name=\"password-check\" />\n " +
        "<p>이메일</p>\n" +
        "<input type=\"text\" name=\"email\" />\n " +
        "<p>\n" +
        "<button type=\"submit\">회원가입</button>\n" +
        "</p>\n" +
        "</form>\n";
  }
}
