package utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FormParserTest {
  @Test
  void parseSufficientRegisterInformation() {
    FormParser formParser = new FormParser();

    Map<String, String> formData = formParser.parse(
        "name=inu&id=hsjk&password=1234&password-check=1234&email=aa@bb.cd");

    assertTrue(
        formData.containsKey("name"),
        "formData에 name key 없음\n" + formData);
    assertTrue(
        formData.containsKey("id"),
        "formData에 id key 없음\n" + formData);
    assertTrue(
        formData.containsKey("password"),
        "formData에 password key 없음\n" + formData);
    assertTrue(
        formData.containsKey("password-check"),
        "formData에 password-check key 없음\n" + formData);
    assertTrue(
        formData.containsKey("email"),
        "formData에 email key 없음\n" + formData);
  }

  @Test
  void parseInsufficientRegisterInformation() {
    FormParser formParser = new FormParser();

    Map<String, String> formData = formParser.parse(
        "id=hsjk&password=1234&email=aa@bb.cd");

    assertFalse(
        formData.containsKey("name"),
        "formData의 name key 오류\n" + formData);
    assertTrue(
        formData.containsKey("id"),
        "formData의 id key 없음\n" + formData);
    assertTrue(
        formData.containsKey("password"),
        "formData의 password key 없음\n" + formData);
    assertFalse(
        formData.containsKey("password-check"),
        "formData의 password-check key 오류\n" + formData);
    assertTrue(
        formData.containsKey("email"),
        "formData의 email key 없음\n" + formData);
  }
}
