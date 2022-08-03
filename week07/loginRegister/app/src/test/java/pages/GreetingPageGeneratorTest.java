package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingPageGeneratorTest {
  @Test
  void html() {
    PageGenerator pageGenerator = new GreetingPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("안녕하세요!"), "메시지 문제 " + html);
    assertTrue(html.contains("<button><a href =\"/login\">"),
        "로그인 버튼 문제 " + html);
    assertTrue(html.contains("<button><a href =\"/registration\">"),
        "회원 가입 버튼 문제 " + html);
  }
}
