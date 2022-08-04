package pages;

import utils.RegistrationFormChecker;

public class RegistrationFailPageGenerator extends PageGenerator {
  private final String status;

  public RegistrationFailPageGenerator(String status) {
    super();

    this.status = status;
  }

  @Override
  public String content() {
    return switch (status) {
      case (RegistrationFormChecker.INSUFFICIENT)
          -> insufficient();
      case (RegistrationFormChecker.ALREADY_EXISTING_ID)
          -> alreadyExistingId();
      case (RegistrationFormChecker.NOT_EQUAL_TO_PASSWORD_CHECK)
          -> notEqualToPasswordCheck();
      default -> "";
    };
  }

  public String insufficient() {
    return "<h2>입력되지 않은 정보가 있습니다. 다시 확인해주세요.</h2>\n" +
        "<a href=\"register\">되돌아가기</a>\n";
  }

  public String alreadyExistingId() {
    return "<h2>이미 등록된 아이디입니다.</h2>\n" +
        "<a href=\"register\">되돌아가기</a>\n";
  }

  public String notEqualToPasswordCheck() {
    return "<h2>입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요.</h2>\n" +
        "<a href=\"register\">되돌아가기</a>\n";
  }
}
