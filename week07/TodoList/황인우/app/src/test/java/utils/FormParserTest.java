package utils;

import models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormParserTest {
  @Test
  void parse() {
    FormParser formParser = new FormParser();

    String content = formParser.parse("task=소고기 사오기");

    assertEquals("소고기 사오기", content);
  }

  @Test
  void parseWithBlank() {
    FormParser formParser = new FormParser();

    String content = formParser.parse("");

    assertEquals("", content);
  }
}
