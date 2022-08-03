package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdNotFoundPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new IdNotFoundPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("사용자를 찾을 수 없습니다. 다시 시도해주세요."),
        "안내 메시지 문제" + html);
    assertTrue(html.contains("되돌아가기"), "되돌아가기 링크 문제" + html);
  }
}
