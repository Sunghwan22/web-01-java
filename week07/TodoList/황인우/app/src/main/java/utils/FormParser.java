package utils;

import models.Task;

public class FormParser {
  public Task parse(String text) {
    String[] keyAndValue = text.split("=");

    if (keyAndValue.length == 2) {
      String content = keyAndValue[1];

      return new Task(content);
    }

    return null;
  }
}
