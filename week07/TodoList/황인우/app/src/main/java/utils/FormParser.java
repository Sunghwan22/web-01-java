package utils;

public class FormParser {
  public String parse(String text) {
    String[] keyAndValue = text.split("=");

    if (keyAndValue.length == 2) {
      return keyAndValue[1];
    }

    return "";
  }
}
