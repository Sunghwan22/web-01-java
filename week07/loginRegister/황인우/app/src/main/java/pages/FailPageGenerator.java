package pages;

import utils.LoginFormChecker;
import utils.RegistrationFormChecker;

public class FailPageGenerator extends PageGenerator {
  private String status;

  public FailPageGenerator(String status) {
    super();

    this.status = status;
  }

  @Override
  public String content() {
    return switch (status) {
      case (LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS),
          (RegistrationFormChecker.INSUFFICIENT_REGISTRATION_INPUTS)
          -> insufficient();
      case (LoginFormChecker.NOT_EXISTING_ID) -> notExistingId();
      case (LoginFormChecker.INCORRECT_PASSWORD) -> incorrectPassword();
      case (RegistrationFormChecker.ALREADY_EXISTING_ID) -> alreadyExistingId();
      case (RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK)
          -> notEqualToPasswordCheck();
      default -> "";
    };
  }

  public String insufficient() {
    return "<p class=\"notification\">입력되지 않은 정보가 있습니다. 다시 확인해주세요.</p>\n" +
        "<a href=\"" + link() + "\">되돌아가기</a>\n";
  }

  public String link() {
    return status.equals(LoginFormChecker.INSUFFICIENT_LOGIN_INPUTS)
        ? "login"
        : "register";
  }

  public String notExistingId() {
    return "<p class=\"notification\">사용자를 찾을 수 없습니다. 다시 시도해주세요.</p>\n" +
        "<a href=\"login\">되돌아가기</a>\n";
  }

  public String incorrectPassword() {
    return "<p class=\"notification\">비밀번호가 일치하지 않습니다. 다시 시도해주세요.</p>\n" +
        "<a href=\"login\">되돌아가기</a>\n";
  }

  public String alreadyExistingId() {
    return "<p class=\"notification\">이미 등록된 아이디입니다.</p>\n" +
        "<a href=\"register\">되돌아가기</a>\n";
  }

  public String notEqualToPasswordCheck() {
    return "<p class=\"notification\">입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요.</p>\n" +
        "<a href=\"register\">되돌아가기</a>\n";
  }
}
