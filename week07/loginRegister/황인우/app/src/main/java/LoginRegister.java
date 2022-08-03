import com.sun.net.httpserver.HttpServer;
import pages.*;
import utils.FormParser;
import utils.MessageWriter;
import utils.RegistrationFormChecker;
import utils.RequestBodyReader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class LoginRegister {
  private RegistrationFormChecker registrationFormChecker;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() {
    registrationFormChecker = new RegistrationFormChecker();
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
      Map<String, String> formData = formParser.parse(line);

//      System.out.println(formData);

      String method = exchange.getRequestMethod();

      PageGenerator pageGenerator = process(path, method, formData);
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }

  public PageGenerator process(
      String path, String method, Map<String, String> formData) {
    return switch (path) {
      case "/login" -> processLogin(method, formData);
      case "/register" -> processRegister(method, formData);
      default -> new GreetingPageGenerator();
    };
  }

  public PageGenerator processRegister(String method, Map<String, String> formData) {
    if (method.equals("GET")) {
      return processRegisterGet();
    }

    return processRegisterPost(formData);
  }

  public PageGenerator processRegisterGet() {
    return new RegisterPageGenerator();
  }

  public PageGenerator processRegisterPost(Map<String, String> formData) {
    // TODO: 회원가입 처리 과정 및 처리 페이지 작성 필요
    String status = registrationFormChecker.check(formData);


    return null;
  }

  public PageGenerator processLogin(String method, Map<String, String> formData) {
    if (method.equals("GET")) {
      return processLoginGet();
    }

    return processLoginPost(formData);
  }

  public PageGenerator processLoginGet() {
    return new LoginPageGenerator();
  }

  public PageGenerator processLoginPost(Map<String, String> formData) {
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
