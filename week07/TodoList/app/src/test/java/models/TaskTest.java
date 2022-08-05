package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
  @Test
  void creation() {
    Task task = new Task("투두리스트 오늘 안에 완성시키기", 1);

    assertEquals("투두리스트 오늘 안에 완성시키기", task.content());
  }

  @Test
  void csvRow() {
    Task task = new Task("드가자", 1);
    task.toCsvRow();

    assertEquals("드가자,1", task.toCsvRow());
  }
}
