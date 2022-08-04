package pages;

import utils.AccountValidityChecker;
import utils.RegistrationFormChecker;

public class LoginFailPageGenerator extends PageGenerator {
  private String status;

  public LoginFailPageGenerator(String status) {
    super();

    this.status = status;
  }

  @Override
  public String content() {
    return switch (status) {
      case (AccountValidityChecker.INSUFFICIENT)
          -> insufficient();
      case (AccountValidityChecker.NOT_EXISTING_ID)
          -> notExistingId();
      case (AccountValidityChecker.INCORRECT_PASSWORD)
          -> incorrectPassword();
      default -> "";
    };
  }

  public String insufficient() {
    return "<p class=\"notification\">입력되지 않은 정보가 있습니다. 다시 확인해주세요.</p>\n" +
        "<a href=\"login\">되돌아가기</a>\n";
  }

  public String notExistingId() {
    return "<p class=\"notification\">사용자를 찾을 수 없습니다. 다시 시도해주세요.</p>\n" +
        "<a href=\"login\">되돌아가기</a>\n";
  }

  public String incorrectPassword() {
    return "<p class=\"notification\">비밀번호가 일치하지 않습니다. 다시 시도해주세요.</p>\n" +
        "<a href=\"login\">되돌아가기</a>\n";
  }
}
