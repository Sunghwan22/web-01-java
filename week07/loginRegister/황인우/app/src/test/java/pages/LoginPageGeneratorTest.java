package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new LoginPageGenerator();

    String content = pageGenerator.content();

    assertTrue(
        content.contains("<h2>로그인</h2>"),
        "로그인 제목 오류\n" + content);
    assertTrue(
        content.contains("아이디 </label>"),
        "아이디 메세지 및 입력란 오류\n" + content);
    assertTrue(
        content.contains("비밀번호 </label>"),
        "비밀번호 메세지 및 입력란 오류\n" + content);
    assertTrue(
        content.contains("<button type=\"submit\">로그인"),
        "로그인 버튼 오류\n" + content);
  }
}
