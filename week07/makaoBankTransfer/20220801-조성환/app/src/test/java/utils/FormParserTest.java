package utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FormParserTest {
  @Test
  void parse() {
    FormParser formParser = new FormParser();

    Map<String, String> formData = formParser.parse("to=1234&amount=1000");

    assertEquals("1234", formData.get("to"));
    assertEquals("1000", formData.get("amount"));
  }

  @Test
  void parseWithBlank() {
    FormParser formParser = new FormParser();

    Map<String, String> formData = formParser.parse("");

    assertFalse(formData.containsKey("to "));
  }
}
