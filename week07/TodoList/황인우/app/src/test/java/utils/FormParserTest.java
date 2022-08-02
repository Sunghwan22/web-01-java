package utils;

import models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormParserTest {
  @Test
  void parse() {
    FormParser formParser = new FormParser();

    Task newlyAddedTask = formParser.parse("task=소고기 사오기");

    assertEquals("소고기 사오기", newlyAddedTask.content());
  }

  @Test
  void parseWithBlank() {
    FormParser formParser = new FormParser();

    Task newlyAddedTask = formParser.parse("");

    assertNull(newlyAddedTask);
  }
}
