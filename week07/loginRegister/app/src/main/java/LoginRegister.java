import com.sun.net.httpserver.HttpServer;
import models.Account;
import pages.DuplicateIdPageGenerator;
import pages.GreetingPageGenerator;
import pages.LoginPageGenerator;
import pages.LoginSuccessPageGenerator;
import pages.NotEnteredInformationPageGenerator;
import pages.PageGenerator;
import pages.PasswordNotEqualsPageGenerator;
import pages.RegisterPageGenerator;
import pages.SuccessRegisterPageGenerator;
import pages.WrongPasswordPageGenerator;
import repositories.AccountRepository;
import utils.AccountLoader;
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

  private final AccountRepository accountRepository;
  private final AccountLoader accountLoader;
  private final FormParser formParser;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() {
     accountRepository = new AccountRepository();

     formParser = new FormParser();

     accountLoader = new AccountLoader();
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

      Map<String, String> formData = formParser.parse(requestBody);

      PageGenerator pageGenerator = process(path, method, formData);

      String content = pageGenerator.html();

      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });
    httpServer.start();
    System.out.println("http://localhost:8000");
  }

  private PageGenerator process(String path,
                                String method,
                                Map<String, String> formData) {
    return switch (path) {
      case "/login" -> processLogin(method, formData);
      case "/registration" -> processRegistraion(method, formData);
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
    if (method.equals("GET")) {
      return processRegistraionGet();
    }

    return processRegistraionPost(formData);
  }

  private RegisterPageGenerator processRegistraionGet() {
    return new RegisterPageGenerator();
  }

  private PageGenerator processRegistraionPost(Map<String, String> formData) {
    if (isaBlankInformation(formData)) {
      return new NotEnteredInformationPageGenerator();
    }

    if (isPasswordcheck(formData)) {
      return new PasswordNotEqualsPageGenerator();
    }

    if(isDuplicatedId(formData)) {
      return new DuplicateIdPageGenerator();
    }

    Account account = new Account(
        formData.get("name"),
        formData.get("id"),
        formData.get("password"),
        formData.get("email"));

    accountRepository.accounts().put(account.id(),  account);

    return new SuccessRegisterPageGenerator();
  }

  private boolean isDuplicatedId(Map<String, String> formData) {
    return !(accountRepository.find(formData.get("id")) == null);
  }

  private boolean isPasswordcheck(Map<String, String> formData) {
    return !formData.get("password").equals(formData.get("password-check"));
  }

  private boolean isaBlankInformation(Map<String, String> formData) {
    return formData.get("name") == null ||
        formData.get("id") == null ||
        formData.get("password") == null ||
        formData.get("password-check") == null ||
        formData.get("email") == null;
  }
}
