package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingPageGeneratorTest {
  @Test
  void text() {
    PageGenerator pageGenerator = new GreetingPageGenerator();

    assertEquals("Hello, world!", pageGenerator.html());
  }

  @Test
  void textWithName() {
    PageGenerator pageGenerator = new GreetingPageGenerator("Ashal");

    assertEquals("Hello, Ashal!", pageGenerator.html());
  }

  @Test
  void textWithEmptyName() {
    PageGenerator pageGenerator = new GreetingPageGenerator("");

    assertEquals("Hello, world!", pageGenerator.html());
  }

  @Test
  void textWithBlankName() {
    PageGenerator pageGenerator = new GreetingPageGenerator("    ");

    assertEquals("Hello, world!", pageGenerator.html());
  }
}
