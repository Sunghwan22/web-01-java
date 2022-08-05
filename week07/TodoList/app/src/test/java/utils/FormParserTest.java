package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormParserTest {
  @Test
  void parse() {
    FormParser formParser = new FormParser();

    assertEquals("안녕하세요", formParser.parse("task=안녕하세요"));
  }
}
