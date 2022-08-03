import com.sun.net.httpserver.HttpServer;
import pages.GreetingPageGenerator;
import pages.PageGenerator;
import utils.MessageWriter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class LoginRegister {
  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      PageGenerator pageGenerator = new GreetingPageGenerator();
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }
}
