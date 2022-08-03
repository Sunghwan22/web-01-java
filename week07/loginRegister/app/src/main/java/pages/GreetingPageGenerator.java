package pages;

public class GreetingPageGenerator extends PageGenerator {

  @Override
  public String content() {
    return "<h1>안녕하세요!</h1>\n" +
        "<button><a href =\"/login\">로그인</a></button>\n" +
        "<button><a href =\"/registration\">회원가입</a></button>\n";
  }
}
