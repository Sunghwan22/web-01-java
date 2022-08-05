package utils;

public class RegistrationNumber {
  public static int RegistrationNumber = 0;

  public static int giveRegistrationNumber() {
    RegistrationNumber += 1;
    return RegistrationNumber;
  }

  public static void setRegistrationNumber(int loadRegistrationNumber) {
    RegistrationNumber = loadRegistrationNumber;
  }

  public static int registrationNumberNumber() {
    return RegistrationNumber;
  }
}
