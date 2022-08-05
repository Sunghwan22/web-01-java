package models;

import utils.RegistrationNumber;

// 식별자를 만들어주자 식별자는 상태값이 아니라 고유의 번호 같은거
public class Task {
  private final String content;
  private int registrationNumber;

  public Task(String content, int registrationNumber) {
    this.content = content;
    this.registrationNumber = RegistrationNumber.giveRegistrationNumber();
  }

  public String content() {
    return content;
  }

  public int RegistrationNumber() {
    return registrationNumber;
  }

  public String toCsvRow() {
    return content + "," + registrationNumber;
  }
}
