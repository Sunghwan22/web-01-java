package pages;

import models.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TodoListPageGenerator extends PageGenerator {
  private List<Task> tasks;

  public TodoListPageGenerator(List<Task> tasks) {
    super();

    this.tasks = tasks;
  }

  @Override
  public String html() {
    return
        "<!DOCTYPE html>\n" +
            "<html lang=\"ko\">\n" +
            "    <head>\n" +
            "        <meta charset=\"UTF-8\" />\n" +
            "        <title>Todo List</title>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <h1>Todo List</h1>\n" +
            "        <form method=\"POST\" enctype=\"text/plain\">\n" +
            "            <label>할 일: </label>\n" +
            "            <input type=\"text\" name=\"task\" />\n" +
            "            <button type=\"submit\">등록</button>\n" +
            "        </form>\n" +
            "        <ul>\n" +
            showTasks() +
            "        </ul>\n" +
            "    </body>\n" +
            "</html>";
  }

  public String showTasks() {
    return tasks.stream()
        .map(task -> "            <li>" + task.content() + "</li>\n")
        .collect(Collectors.joining());
  }
}
