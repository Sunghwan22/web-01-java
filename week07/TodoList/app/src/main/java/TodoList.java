import com.sun.net.httpserver.HttpServer;
import models.Task;
import pages.TodoListPageGenerator;
import utils.FormParser;
import utils.MessageWriter;
import utils.RegistrationNumber;
import utils.RequestBodyReader;
import utils.TaskLoader;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class TodoList {
  private final TaskLoader taskLoader;
  private final List<Task> tasks;

  public static void main(String[] args) throws IOException {
    TodoList application = new TodoList();
    application.run();
  }

  public TodoList() throws FileNotFoundException {
    taskLoader = new TaskLoader();
    tasks = taskLoader.loadTask();
  }

  private void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {

      String method = exchange.getRequestMethod();

      String requestBody = new RequestBodyReader(exchange).body();

      if(method.equals("POST")) {
        String taskContent = new FormParser().parse(requestBody);

        tasks.add(new Task(taskContent, RegistrationNumber.RegistrationNumber));

        taskLoader.writeTask(tasks);
      }

      String content = new TodoListPageGenerator(tasks).html();

      new MessageWriter(exchange).write(content);
    });

    httpServer.start();
    System.out.println("http://localhost:8000");
  }
}
