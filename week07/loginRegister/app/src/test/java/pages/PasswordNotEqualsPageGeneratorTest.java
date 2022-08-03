package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordNotEqualsPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new PasswordNotEqualsPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("<h1>입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요.")
    ,"비밀번호 불일치 메시지 문제" + html);
    assertTrue(html.contains("되돌아가기"),"되돌아가기 메시지 문제" + html);
  }
}
