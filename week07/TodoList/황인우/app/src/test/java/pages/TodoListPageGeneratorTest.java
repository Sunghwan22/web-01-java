package pages;

import models.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListPageGeneratorTest {
  @Test
  void html() {
    List<Task> tasks = new ArrayList<>();

    PageGenerator pageGenerator = new TodoListPageGenerator(tasks);

    String html = pageGenerator.html();

    assertTrue(
        html.contains("<h1>Todo List</h1>"),
        "웹 페이지에 제목이 없거나 테스트 코드와 다릅니다.\n" + html);
    assertTrue(
        html.contains("<label>할 일: </label>"),
        "웹 페이지에 할 일 label이 없거나 테스트 코드와 다릅니다.\n" + html);
    assertTrue(
        html.contains("<input type=\"text\" name=\"task\" />"),
        "웹 페이지에 입력창이 없거나 테스트 코드와 다릅니다.\n" + html);
    assertTrue(
        html.contains("<button type=\"submit\">등록</button>"),
        "웹 페이지에 등록 버튼이 없거나 테스트 코드와 다릅니다.\n" + html);
  }

  @Test
  void showTasks() {
    List<Task> tasks = List.of(
        new Task("할일 1"),
        new Task("할일 2"),
        new Task("할일 3")
    );

    PageGenerator pageGenerator = new TodoListPageGenerator(tasks);

    String html = pageGenerator.html();

    assertTrue(
        html.contains("<li>할일 1</li>"),
        "리스트 예시 추가 로직에 문제가 있습니다.\n" + html);
    assertTrue(
        html.contains("<li>할일 2</li>"),
        "리스트 예시 추가 로직에 문제가 있습니다.\n" + html);
    assertTrue(
        html.contains("<li>할일 3</li>"),
        "리스트 예시 추가 로직에 문제가 있습니다.\n" + html);
  }
}
