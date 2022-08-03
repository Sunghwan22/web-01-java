package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WrongPasswordPageGeneratorTest {
  @Test
  void content()  {
    PageGenerator pageGenerator = new WrongPasswordPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("비밀번호가 일치하지 않습니다"));
    assertTrue(html.contains("되돌아가기"));
  }
}
