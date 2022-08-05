package pages;

public class FailPageGenerator extends PageGenerator {
  private String status;

  public FailPageGenerator(String status) {
    this.status = status;
  }

  @Override
  public String content() {
    return switch (status) {
      case "BLANK_LOGIN_FORM" -> blankLoginForm();
      case "BLANK_REGISTER_FORM" -> blankRegisterForm();
      case "ID_NOT_FOUND" -> idNotFound();
      case "WRONG_PASSWORD" -> wrongPassword();
      case "NOT_EQUAL_PASSWORD" -> notEqualPassword();
      case "ALREADY_EXISTING_USER" -> alreadyExistId();
      default -> "";
    };
  }

  private String blankLoginForm() {
    return "<h1>입력되지 않은 정보가 있습니다. 다시 확인해주세요.</h1>\n" +
        "<a href =\"/login\">되돌아가기</a>\n";
  }

  private String blankRegisterForm() {
    return "<h1>입력되지 않은 정보가 있습니다. 다시 확인해주세요.</h1>\n" +
        "<a href =\"/registration\">되돌아가기</a>\n";
  }

  private String idNotFound() {
    return "<h1>사용자를 찾을 수 없습니다. 다시 시도해주세요.</h1>\n" +
        "<a href =\"/login\">되돌아가기</a>\n";
  }

  private String wrongPassword() {
    return "<h1>비밀번호가 일치하지 않습니다. 다시 확인해주세요.</h1>\n" +
        "<a href =\"/login\">되돌아가기</a>\n";
  }

  private String notEqualPassword() {
    return "<h1>입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인해주세요.</h1>\n" +
        "<a href =\"/registration\">되돌아가기</a>\n";
  }

  private String alreadyExistId() {
    return "<h1>이미 등록된 아이디입니다.</h1>\n" +
        "<a href =\"/registration\">되돌아가기</a>\n";
  }
}
