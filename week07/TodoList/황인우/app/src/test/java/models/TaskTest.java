package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
  @Test
  void creation() {
    Task task = new Task(1, "할 일의 내용");

    assertEquals(1, task.identifier());
    assertEquals("할 일의 내용", task.content());
  }
}
