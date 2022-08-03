package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new LoginPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("<h1>로그인"), "로그인 메시지 문제 " + html);
    assertTrue(html.contains("아이디"), "아이디 메시지 문제" + html);
    assertTrue(html.contains("비밀번호"), "비밀번호 메시지 문제" + html);
    assertTrue(html.contains("<input type=\"text\""), "아이디 비밀번호 입력 문제 " + html);
    assertTrue(html.contains("<button type=\"submit\">"), "버튼 문제 " + html);
  }
}
