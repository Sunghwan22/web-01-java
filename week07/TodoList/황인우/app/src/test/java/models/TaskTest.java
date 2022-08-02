package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
  @Test
  void creation() {
    Task task = new Task("할 일의 내용");

    assertEquals("할 일의 내용", task.content());
  }
}
