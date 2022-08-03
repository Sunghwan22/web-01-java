// Map을 사용해서 아이디를 키값으로하고 비밀번호를 밸류로 지

import com.sun.net.httpserver.HttpServer;
import pages.GreetingPageGenerator;
import pages.LoginPageGenerator;
import pages.PageGenerator;
import pages.RegisterPageGenerator;
import utils.MessageWriter;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Scanner;

public class LoginRegister {
  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  private void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address,0);

    httpServer.createContext("/", exchange -> {

      URI requestURI = exchange.getRequestURI();
      String path = requestURI.getPath();

      InputStream inputStream = exchange.getRequestBody();
      Scanner scanner = new Scanner(inputStream);
      if(scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
      }

      PageGenerator pageGenerator = new GreetingPageGenerator();

     if(path.equals("/login")) {
        pageGenerator = new LoginPageGenerator();
      }

      if(path.equals("/registration")) {
        pageGenerator = new RegisterPageGenerator();
      }

      String content = pageGenerator.html();

      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });
    httpServer.start();
    System.out.println("http://localhost:8000");
  }
}
