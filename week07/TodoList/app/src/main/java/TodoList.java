// Repository가 필요할까?
// repository 는 발견/검색의 기능을 가지고 있는데
// 요구사항에는 검색이나 발견 기능이 없으니까 List로 관리
// map도 굳이 사용할 필요가 없을 것 같음 key value를 얻어서 task의 속성값을 변경해줄 필요가 없으니까

import com.sun.net.httpserver.HttpServer;
import models.Task;
import utils.FormParser;
import utils.MessageWriter;
import utils.RequestBodyReader;
import utils.TaskLoader;
import utils.TodoListPageGenerator;

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
        FormParser formParser = new FormParser();
        String taskContent = formParser.parse(requestBody);

        tasks.add(new Task(taskContent));

        taskLoader.writeTask(tasks);
      }

      TodoListPageGenerator todoListPageGenerator = new TodoListPageGenerator(tasks);
      String content = todoListPageGenerator.html();

      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });

    httpServer.start();
    System.out.println("http://localhost:8000");
  }
}
