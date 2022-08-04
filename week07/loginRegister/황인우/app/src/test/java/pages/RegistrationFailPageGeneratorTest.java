package pages;

import org.junit.jupiter.api.Test;
import utils.RegistrationFormChecker;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFailPageGeneratorTest {
  @Test
  void insufficient() {
    PageGenerator pageGenerator
        = new RegistrationFailPageGenerator(RegistrationFormChecker.INSUFFICIENT);

    String content = pageGenerator.content();

    // 입력되지 않은 정보가 있습니다. 다시 확인해주세요.
    // 되돌아가기

    assertTrue(content.contains(""))
  }

  @Test
  void alreadyExistingId() {

    // 이미 등록된 아이디입니다.
    // 되돌아가기

  }

  @Test
  void notEqualToPasswordCheck() {

    // 입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요.
    // 되돌아가기

  }
}
