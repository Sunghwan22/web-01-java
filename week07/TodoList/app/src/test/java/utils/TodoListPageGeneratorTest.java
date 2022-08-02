package utils;

import models.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListPageGeneratorTest {
  @Test
  void html() {
    List<Task> tasks = new ArrayList<>();

    TodoListPageGenerator todoListPageGenerator  = new TodoListPageGenerator(tasks);

    String html = todoListPageGenerator.html();

    assertTrue(html.contains("TodoList"), "메시지 문제 있음" + html);
    assertTrue(html.contains("할 일"), "메시지 문제 있음" + html);
    assertTrue(html.contains("<input"), "할 일 입력 문제 있음" + html);
    assertTrue(html.contains("<button type=\"submit\">등록</button>"),
        "버튼 문제 있음" + html);
  }

  @Test
  void todoListPageWithTasks() {
    List<Task> tasks = new ArrayList<>();

    tasks.add(new Task("티아이엘 쓰기"));
    tasks.add(new Task("주말에 개같이 공부하기"));

    TodoListPageGenerator todoListPageGenerator = new TodoListPageGenerator(tasks);


  }
}
