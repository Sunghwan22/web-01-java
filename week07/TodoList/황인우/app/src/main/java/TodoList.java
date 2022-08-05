import com.sun.net.httpserver.HttpServer;
import models.Task;
import pages.PageGenerator;
import pages.TodoListPageGenerator;
import utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class TodoList {
  private IdentifierManager identifierManager;

  private List<Task> tasks;

  public static void main(String[] args) throws IOException {
    TodoList application = new TodoList();
    application.run();
  }

  public TodoList() throws FileNotFoundException {
    identifierManager = new IdentifierManager();

    FileLoader fileLoader = new FileLoader();

    tasks = fileLoader.loadTasks("data.csv", identifierManager);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        fileLoader.saveTasks("data.csv", tasks);
      } catch (IOException exception) {
        throw new RuntimeException(exception);
      }
    }));
  }

  public void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      RequestBodyReader requestBodyReader = new RequestBodyReader(exchange);
      String requestBody = requestBodyReader.body();

      FormParser formParser = new FormParser();
      String form = formParser.parse(requestBody);

      PageGenerator pageGenerator = process(form);
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }

  public PageGenerator process(String form) {
    if (!form.isEmpty()) {
      int identifier = identifierManager.assignIdentifier();

      Task task = new Task(identifier, form);

      tasks.add(task);
    }

    return new TodoListPageGenerator(tasks);
  }
}
