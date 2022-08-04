package pages;

public class RegistrationSuccessPageGenerator extends PageGenerator {
  @Override
  public String content() {
    return "<p class=\"notification\">회원 가입이 완료되었습니다.</p>\n" +
        "<a href=\"login\">로그인하기</a>\n";
  }
}
