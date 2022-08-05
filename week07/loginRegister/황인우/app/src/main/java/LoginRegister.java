import models.User;
import com.sun.net.httpserver.HttpServer;
import pages.*;
import repositories.UserRepository;
import services.UserService;
import utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

public class LoginRegister {
  private UserRepository userRepository;

  private UserService userService;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() throws FileNotFoundException {
    FileLoader fileLoader = new FileLoader();

    userRepository = fileLoader.loadData("data.csv");

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        fileLoader.saveData("data.csv", userRepository);
      } catch (IOException exception) {
        throw new RuntimeException(exception);
      }
    }));

    userService = new UserService(userRepository);
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
    String status = userService.checkRegistrationForm(formData);

    if (status.equals(RegistrationFormChecker.ACCEPTED)) {
      userRepository.addUser(
          new User(
              formData.get("name"),
              formData.get("id"),
              formData.get("password"),
              formData.get("email")));

      return new RegistrationSuccessPageGenerator();
    }

    return new FailPageGenerator(status);
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
    String status = userService.checkLoginForm(formData);

    if (status.equals(LoginFormChecker.ACCEPTED)) {
      User found = userRepository.findUser(formData.get("id"));

      return new LoginSuccessPageGenerator(found);
    }

    return new FailPageGenerator(status);
  }
}
