package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new GreetingPageGenerator();

    String content = pageGenerator.content();

    assertTrue(
        content.contains("안녕하세요!"),
        "안녕하세요! 메세지 오류\n" + content);
    assertTrue(
        content.contains("로그인"),
        "로그인 버튼 오류\n" + content);
    assertTrue(
        content.contains("회원가입"),
        "회원가입 버튼 오륲\n" + content);
  }
}
