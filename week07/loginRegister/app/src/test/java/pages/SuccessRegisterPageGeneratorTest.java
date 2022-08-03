package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuccessRegisterPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new SuccessRegisterPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("회원 가입이 완료되었습니다"));
    assertTrue(html.contains("로그인하기"));
  }
}
