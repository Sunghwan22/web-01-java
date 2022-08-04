package pages;

public class RegistrationSuccessPageGenerator extends PageGenerator {
  @Override
  public String content() {
    return "<h2>회원 가입이 완료되었습니다.</h2>\n" +
        "<a href=\"login\">로그인하기</a>\n";
  }
}
