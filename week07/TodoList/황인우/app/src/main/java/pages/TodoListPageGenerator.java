package pages;

public class TodoListPageGenerator extends PageGenerator {
  @Override
  public String html() {
    return
        "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <head>\n" +
            "    <meta charset=\"UTF-8\" />\n" +
            "    <title>Todo List</title>\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <h1>Todo List</h1>\n" +
            "    <form method=\"POST\">" +
            "      <label>할 일: </label>" +
            "      <input type=\"text\" name=\"task\" />" +
            "      <button type=\"submit\">등록</button>" +
            "    </form>" +
            "  </body>\n" +
            "</html>";
  }
}
