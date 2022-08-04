package pages;

import org.junit.jupiter.api.Test;
import utils.AccountValidityChecker;

import static org.junit.jupiter.api.Assertions.*;

class LoginFailPageGeneratorTest {
  @Test
  void insufficient() {
    PageGenerator pageGenerator
        = new LoginFailPageGenerator(
        AccountValidityChecker.INSUFFICIENT);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("입력되지 않은 정보가 있습니다. 다시 확인해주세요."),
        "입력 정보 부족 오류 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }

  @Test
  void notExistingId() {
    PageGenerator pageGenerator
        = new LoginFailPageGenerator(
        AccountValidityChecker.NOT_EXISTING_ID);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("사용자를 찾을 수 없습니다. 다시 시도해주세요."),
        "아이디 없음 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }

  @Test
  void incorrectPassword() {
    PageGenerator pageGenerator
        = new LoginFailPageGenerator(
        AccountValidityChecker.INCORRECT_PASSWORD);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("비밀번호가 일치하지 않습니다. 다시 시도해주세요."),
        "비밀번호 틀림 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }
}
