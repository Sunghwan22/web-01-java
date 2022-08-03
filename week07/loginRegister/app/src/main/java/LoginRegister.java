import com.sun.net.httpserver.HttpServer;
import models.Account;
import pages.GreetingPageGenerator;
import pages.LoginPageGenerator;
import pages.LoginSuccessPageGenerator;
import pages.PageGenerator;
import pages.RegisterPageGenerator;
import pages.SuccessRegisterPageGenerator;
import utils.FormParser;
import utils.MessageWriter;
import utils.RequestBodyReader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginRegister {
  private final List<Account> accounts;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() {
    accounts = new ArrayList<>();
  }

  private void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {

      URI requestURI = exchange.getRequestURI();
      String path = requestURI.getPath();

      String method = exchange.getRequestMethod();

      RequestBodyReader requestBodyReader = new RequestBodyReader(exchange);
      String requestBody = requestBodyReader.body();

      FormParser formParser = new FormParser();
      Map<String, String> formData = formParser.parse(requestBody);


      PageGenerator pageGenerator = process(path,method,formData);

      String content = pageGenerator.html();

      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });
    httpServer.start();
    System.out.println("http://localhost:8000");
  }

  private PageGenerator process(String path,
                                String method,
                                Map<String ,String> formData) {
    return switch (path) {
      case "/login" -> processLogin(method,formData);
      case "/registration" -> processRegistraion(method,formData);
      default -> new GreetingPageGenerator();
    };
  }

  private PageGenerator processLogin(String method, Map<String, String> formData) {
    if(method.equals("GET")) {
      return processLoginGet();
    }
    return processLoginPost(formData);
  }

  private LoginPageGenerator processLoginGet() {
    return new LoginPageGenerator();
  }

  private PageGenerator processLoginPost(Map<String, String> formData) {
    //Todo 어카운트에 있는 아이디와 중복 되는지 검사해야함
//    if("비밀번호틀림") {
//      return  new WrongPasswordPageGenerator();
//    }
//    if("아이디 틀림") {
//      return  new IdNotFoundPageGenerator();
//    }

    return new LoginSuccessPageGenerator();
  }

  private PageGenerator processRegistraion(String method, Map<String, String> formData) {
    if(method.equals("GET")) {
      return processRegistraionGet();
    }
    return processRegistraionPost(formData);
  }

  private RegisterPageGenerator processRegistraionGet() {
    return new RegisterPageGenerator();
  }

  private PageGenerator processRegistraionPost(Map<String, String> formData) {
    // todo 이미 등록되있는 계정인지 확인
//    if("조건 검사 ") {
//      return new DuplicateIdPageGenerator();
//    }
//    if("비밀번호 확인이 틀릴 때 ") {
//      return  new PasswordNotEqualsPageGenerator();
//    }
    // 여기서 account가 생성이 되는데
    return new SuccessRegisterPageGenerator();
  }
}
