package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginSuccessPageGeneratorTest {
  @Test
  void content() {
    String name = "조성환";
    PageGenerator pageGenerator = new LoginSuccessPageGenerator(name);

    String html = pageGenerator.html();

    assertTrue(html.contains("마카오 뱅크"), "메시지 문제" + html);
    assertTrue(html.contains("안녕하세요, 조성환님! 행복을 적립하는 마카오뱅크입니다"),
        "인사 메시지 문제" + html);
  }
}
