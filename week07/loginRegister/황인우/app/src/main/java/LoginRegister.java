import models.Account;
import pages.RegistrationFailPageGenerator;
import com.sun.net.httpserver.HttpServer;
import pages.*;
import repositories.AccountRepository;
import utils.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

public class LoginRegister {
  private AccountRepository accountRepository;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() {
    accountRepository = new AccountRepository();
  }

  public void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      URI uri = exchange.getRequestURI();
      String path = uri.getPath();

      String line = new RequestBodyReader(exchange).body();

      FormParser formParser = new FormParser();
      Map<String, String> formData = formParser.parse(line);

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
      case "/register" -> processRegistration(method, formData);
      case "/login" -> processLogin(method, formData);
      default -> new GreetingPageGenerator();
    };
  }

  public PageGenerator processRegistration(String method, Map<String, String> formData) {
    if (method.equals("GET")) {
      return processRegistrationGet();
    }

    return processRegistrationPost(formData);
  }

  public PageGenerator processRegistrationGet() {
    return new RegistrationPageGenerator();
  }

  public PageGenerator processRegistrationPost(Map<String, String> formData) {
    String status = new RegistrationFormChecker(accountRepository).check(formData);

    if (status.equals(RegistrationFormChecker.ACCEPTED)) {
      // TODO: 회원가입 조건 검사를 완료했으니 진짜 처리 과정 작성 필요
      accountRepository.addAccount(
          new Account(
              formData.get("name"),
              formData.get("id"),
              formData.get("password"),
              formData.get("email")));

      return new RegistrationSuccessPageGenerator();
    }

    return new RegistrationFailPageGenerator(status);
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
    String status = new AccountValidityChecker().check(formData);

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
