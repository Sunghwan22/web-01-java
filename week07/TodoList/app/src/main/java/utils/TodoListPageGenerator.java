package utils;

import models.Task;

import java.util.List;

public class TodoListPageGenerator {
  private List<Task> tasks;

  public TodoListPageGenerator(List<Task> tasks) {
    this.tasks = tasks;
  }

  public String html() {
    return "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "<meta charset=\"UTF-8\" />\n" +
        "<title>TodoList</title>\n" +
        "</head>\n" +
        "<body>\n" +
        content() +
        "<ul>\n" +
        taskList() +
        "/<ul>\n" +
        "</body>\n" +
        "</html>";
  }

  private String taskList() {
    // 포 문 돌면서 추가를 해줘도 될 것 같고
    String html = "";

    for (Task task : tasks) {
      html += "<li>" + task.content() + "</li>\n";
    }
    return html;
  }

  public String content() {
    return "<h1>TodoList</h1>\n" +
        "<form method=\"POST\">" +
        "<label>할 일: </label>\n" +
        "<input type=\"text\" name=\"task\"/>\n" +
        "<button type=\"submit\">등록</button>\n" +
        "</form>\n";
  }
}
