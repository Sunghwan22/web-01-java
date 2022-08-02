package utils;

import models.Task;

public class FormParser {
  public String parse(String text) {

    String[] keyAndValue = text.split("=");

    return keyAndValue[1];
  }
}
