package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
  @Test
  void creation() {
    Task task = new Task("투두리스트 오늘 안에 완성시키기");

    assertEquals("투두리스트 오늘 안에 완성시키기", task.content());
  }

  @Test
  void csvRow() {
    //어차피 String 타입인데 할 필요 있나?
    Task task = new Task("드가자");
    task.toCsvRow();

    assertEquals("드가자", task.toCsvRow());
  }
}
