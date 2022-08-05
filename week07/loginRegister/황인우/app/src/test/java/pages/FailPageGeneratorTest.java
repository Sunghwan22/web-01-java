package pages;

import org.junit.jupiter.api.Test;
import utils.LoginFormChecker;
import utils.RegistrationFormChecker;

import static org.junit.jupiter.api.Assertions.*;

class FailPageGeneratorTest {
  @Test
  void insufficientLoginInputs() {
    PageGenerator pageGenerator
        = new FailPageGenerator(
        LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS);

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
        = new FailPageGenerator(
        LoginFormChecker.NOT_EXISTING_ID);

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
        = new FailPageGenerator(
        LoginFormChecker.INCORRECT_PASSWORD);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("비밀번호가 일치하지 않습니다. 다시 시도해주세요."),
        "비밀번호 틀림 안내 메세지 오류\n" + content);
    assertTrue(
        content.contains("되돌아가기"),
        "되돌아가기 링크 오류\n" + content);
  }

  @Test
  void insufficientRegistrationInputs() {
    PageGenerator pageGenerator
        = new FailPageGenerator(
        RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS);

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
        = new FailPageGenerator(
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
        = new FailPageGenerator(
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
