import com.sun.net.httpserver.HttpServer;
import pages.*;
import utils.FormParser;
import utils.MessageWriter;
import utils.RequestBodyReader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

public class LoginRegister {
  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      URI uri = exchange.getRequestURI();
      String path = uri.getPath();

      String line = new RequestBodyReader(exchange).body();

//      System.out.println(line);

      FormParser formParser = new FormParser();
      List<String> formValues = formParser.parse(line);

//      System.out.println(formValues);

      String method = exchange.getRequestMethod();

      PageGenerator pageGenerator = process(path, method, formValues);
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }

  public PageGenerator process(
      String path, String method, List<String> formValues) {
    return switch (path) {
      case "/login" -> processLogin(method, formValues);
      case "/register" -> processRegister(method, formValues);
      default -> new GreetingPageGenerator();
    };
  }

  private PageGenerator processRegister(String method, List<String> formValues) {
    if (method.equals("GET")) {

    }

    return new RegisterPageGenerator();
  }

  public PageGenerator processLogin(String method, List<String> formValues) {
    if (method.equals("GET")) {
      return processLoginGet();
    }

    return processLoginPost(formValues);
  }

  public PageGenerator processLoginGet() {
    return new LoginPageGenerator();
  }

  public PageGenerator processLoginPost(List<String> formValues) {
    //TODO: 로그인 조건 검사 필요

    if (true) {
      return new LoginSuccessPageGenerator();
    }

    if (false) {
//      return new LoginWrongIdPageGenerator();
    }

//    return new LoginWrongPasswordPageGenerator();
    return null;
  }
}
