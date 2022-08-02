import com.sun.net.httpserver.HttpServer;
import models.Task;
import pages.PageGenerator;
import pages.TodoListPageGenerator;
import utils.FileLoader;
import utils.FormParser;
import utils.MessageWriter;
import utils.RequestBodyReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class TodoList {
  private List<Task> tasks;

  public static void main(String[] args) throws IOException {
    TodoList application = new TodoList();
    application.run();
  }

  public TodoList() throws FileNotFoundException {
    FileLoader fileLoader = new FileLoader();

    tasks = fileLoader.loadTasks("data.csv");

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
      Task newlyAddedTask = formParser.parse(requestBody);

      if (newlyAddedTask != null) {
        tasks.add(newlyAddedTask);
      }

      PageGenerator pageGenerator = new TodoListPageGenerator(tasks);
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }
}
