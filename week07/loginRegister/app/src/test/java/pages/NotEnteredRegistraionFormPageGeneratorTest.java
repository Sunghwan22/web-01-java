package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEnteredRegistraionFormPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new NotEnteredInformationPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("입력되지 않은 정보가 있습니다. 다시 확인해주세요."),
        "안내 메시지 문제" + html);
    assertTrue(html.contains("되돌아가기"), "되돌아가기 링크 문제" + html);
  }
}
