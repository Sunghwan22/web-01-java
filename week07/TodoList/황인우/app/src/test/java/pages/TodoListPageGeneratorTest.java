package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListPageGeneratorTest {
  @Test
  void html() {
    PageGenerator pageGenerator = new TodoListPageGenerator();

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
}
