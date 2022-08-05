package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailPageGeneratorTest {
  @Test
  void  blankLoginForm() {
    PageGenerator pageGenerator =
        new FailPageGenerator("BLANK_LOGIN_FORM");

   String html = pageGenerator.content();

   assertTrue(html.contains("입력되지 않은 정보가 있습니다. 다시 확인해주세요."),
       "제목 문제" + html);
   assertTrue(html.contains("<a href =\"/login\">"), "링크 문제" + html);
  }

  @Test
  void  blankRegisterForm() {
    PageGenerator pageGenerator =
        new FailPageGenerator("BLANK_REGISTER_FORM");

    String html = pageGenerator.content();

    assertTrue(html.contains("입력되지 않은 정보가 있습니다. 다시 확인해주세요."),
        "제목 문제" + html);
    assertTrue(html.contains("<a href =\"/registration\">"), "링크 문제" + html);
  }

  @Test
  void  idNotFound() {
    PageGenerator pageGenerator =
        new FailPageGenerator("ID_NOT_FOUND");

    String html = pageGenerator.content();

    assertTrue(html.contains("사용자를 찾을 수 없습니다. 다시 시도해주세요."),
        "제목 문제" + html);
    assertTrue(html.contains("되돌아가기"), "링크 문제" + html);
  }

  @Test
  void  wrongPassword() {
    PageGenerator pageGenerator =
        new FailPageGenerator("WRONG_PASSWORD");

    String html = pageGenerator.content();

    assertTrue(html.contains("비밀번호가 일치하지 않습니다. 다시 확인해주세요."),
        "제목 문제" + html);
    assertTrue(html.contains("되돌아가기"), "링크 문제" + html);
  }

  @Test
  void  notEqualPassword() {
    PageGenerator pageGenerator =
        new FailPageGenerator("NOT_EQUAL_PASSWORD");

    String html = pageGenerator.content();

    assertTrue(html.contains("입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요."),
        "제목 문제" + html);
    assertTrue(html.contains("되돌아가기"), "링크 문제" + html);
  }

  @Test
  void  alreadyExistId() {
    PageGenerator pageGenerator =
        new FailPageGenerator("ALREADY_EXISTING_USER");

    String html = pageGenerator.content();

    assertTrue(html.contains("이미 등록된 아이디입니다."),
        "제목 문제" + html);
    assertTrue(html.contains("되돌아가기"), "링크 문제" + html);
  }
}
