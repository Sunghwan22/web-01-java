import com.sun.net.httpserver.HttpServer;
import pages.PageGenerator;
import pages.TodoListPageGenerator;
import utils.MessageWriter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TodoList {
  public static void main(String[] args) throws IOException {
    TodoList application = new TodoList();
    application.run();
  }

  public void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      PageGenerator pageGenerator = new TodoListPageGenerator();
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }
}
