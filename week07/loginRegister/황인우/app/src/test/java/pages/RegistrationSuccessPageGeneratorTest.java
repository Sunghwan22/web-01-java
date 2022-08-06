package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSuccessPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new RegistrationSuccessPageGenerator();

    String content = pageGenerator.content();

    assertTrue(
        content.contains("회원 가입이 완료되었습니다."),
        "회원 가입 완료 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("로그인하기"),
        "로그인하기 링크 메세지 오류\n" + content);
  }
}
