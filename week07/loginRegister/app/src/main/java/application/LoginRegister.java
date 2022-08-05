package application;

import com.sun.net.httpserver.HttpServer;
import models.User;
import pages.FailPageGenerator;
import pages.GreetingPageGenerator;
import pages.LoginPageGenerator;
import pages.LoginSuccessPageGenerator;
import pages.PageGenerator;
import pages.RegisterPageGenerator;
import pages.SuccessRegisterPageGenerator;
import repositories.UserRepository;
import services.LoginService;
import services.RegisterService;
import utils.FormParser;
import utils.MessageWriter;
import utils.RequestBodyReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

public class LoginRegister {
  private final UserRepository userRepository;
  private final FormParser formParser;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() throws FileNotFoundException {
    userRepository = new UserRepository();

    formParser = new FormParser();
  }

  private void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {

      URI requestURI = exchange.getRequestURI();
      String path = requestURI.getPath();

      String method = exchange.getRequestMethod();

      String requestBody = new RequestBodyReader(exchange).body();

      Map<String, String> formData = formParser.parse(requestBody);

      String content = process(path, method, formData).html();

      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });
    httpServer.start();
    System.out.println("http://localhost:8000");
  }

  private PageGenerator process(String path,
                                String method,
                                Map<String, String> formData) throws IOException {
    return switch (path) {
      case "/login" -> processLogin(method, formData);
      case "/registration" -> processRegistration(method, formData);
      default -> new GreetingPageGenerator();
    };
  }

  private PageGenerator processLogin(String method, Map<String, String> formData) {
    if (method.equals("GET")) {
      return processLoginGet();
    }
    return processLoginPost(formData);
  }

  private LoginPageGenerator processLoginGet() {
    return new LoginPageGenerator();
  }

  private PageGenerator processLoginPost(Map<String, String> formData) {
    String status = new LoginService(userRepository).LoginFormCheck(formData);

    if(status.equals(LoginService.SUCCESS)) {
      User findUser = userRepository.find(formData.get("id"));

      String name = findUser.name();

      return new LoginSuccessPageGenerator(name);
    }

    return new FailPageGenerator(status);
  }

  private PageGenerator processRegistration(String method, Map<String, String> formData) throws IOException {
    if (method.equals("GET")) {
      return processRegistrationGet();
    }

    return processRegistrationPost(formData);
  }

  private RegisterPageGenerator processRegistrationGet() {
    return new RegisterPageGenerator();
  }

  private PageGenerator processRegistrationPost(Map<String, String> formData) throws IOException {
    String status = new RegisterService(userRepository).registrationFormCheck(formData);

    if (status.equals("PASSED")) {
      User user = new User(
          formData.get("name"),
          formData.get("id"),
          formData.get("password"),
          formData.get("email"));

      userRepository.users().put(user.id(), user);

      userRepository.write(userRepository.users());

      return new SuccessRegisterPageGenerator();
    }
    return new FailPageGenerator(status);
  }
}
