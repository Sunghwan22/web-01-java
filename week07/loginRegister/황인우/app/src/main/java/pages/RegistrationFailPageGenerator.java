package pages;

import utils.RegistrationFormChecker;

public class RegistrationFailPageGenerator extends PageGenerator {
  private String status;

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
    };
  }

  public String insufficient() {
    return "";
  }

  public String alreadyExistingId() {
    return "";
  }

  public String notEqualToPasswordCheck() {
    return "";
  }
}
