package utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FormParserTest {
  @Test
  void parse() {
    FormParser formParser = new FormParser();

    Map<String, String> formData = formParser.parse(
        "name=조성환&id=tidls45&password=1234&password-check=1234&email=tidls45@");

    assertEquals("조성환", formData.get("name"));
    assertEquals("tidls45", formData.get("id"));
    assertEquals("1234", formData.get("password"));
    assertEquals("1234", formData.get("password-check"));
    assertEquals("tidls45@", formData.get("email"));
  }

  @Test
  void parseWithBlank() {
    FormParser formParser = new FormParser();

    Map<String, String> formData = formParser.parse(
        "");

    assertFalse(formData.containsKey("to"));
    assertFalse(formData.containsKey("id"));
    assertFalse(formData.containsKey("password"));
    assertFalse(formData.containsKey("password-check"));
    assertFalse(formData.containsKey("email"));
  }
}
