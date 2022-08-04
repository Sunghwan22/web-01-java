package pages;

import org.junit.jupiter.api.Test;
import utils.RegistrationFormChecker;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFailPageGeneratorTest {
  @Test
  void insufficient() {
    PageGenerator pageGenerator
        = new RegistrationFailPageGenerator(
            RegistrationFormChecker.INSUFFICIENT);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("입력되지 않은 정보가 있습니다. 다시 확인해주세요."),
        "입력 정보 부족 오류 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }

  @Test
  void alreadyExistingId() {
    PageGenerator pageGenerator
        = new RegistrationFailPageGenerator(
            RegistrationFormChecker.ALREADY_EXISTING_ID);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("이미 등록된 아이디입니다."),
        "중복 아이디 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }

  @Test
  void notEqualToPasswordCheck() {
    PageGenerator pageGenerator
        = new RegistrationFailPageGenerator(
            RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요."),
        "비밀번호 확인 불일치 오류 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }
}
