package application;

import com.sun.net.httpserver.HttpServer;
import models.Account;
import pages.DuplicateIdPageGenerator;
import pages.GreetingPageGenerator;
import pages.IdNotFoundPageGenerator;
import pages.LoginPageGenerator;
import pages.LoginSuccessPageGenerator;
import pages.NotEnteredInformationPageGenerator;
import pages.NotEnteredLoginForm;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

public class LoginRegister {

  private final AccountRepository accountRepository;
  private final AccountLoader accountLoader;
  private final FormParser formParser;

  public static void main(String[] args) throws IOException {
    LoginRegister application = new LoginRegister();
    application.run();
  }

  public LoginRegister() throws FileNotFoundException {
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
                                Map<String, String> formData) throws IOException {
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
    Account account = accountRepository.find(formData.get("id"));

    if (isaBlankLoginForm(formData)) {
      return new NotEnteredLoginForm();
    }

    if (account == null) {
      return new IdNotFoundPageGenerator();
    }

    if (isConfirmPassword(formData, account)) {
      return new WrongPasswordPageGenerator();
    }
    String name = account.name();
    return new LoginSuccessPageGenerator(name);
  }

  private boolean isaBlankLoginForm(Map<String, String> formData) {
    return formData.get("id") == null ||
        formData.get("password") == null;
  }

  private boolean isConfirmPassword(Map<String, String> formData, Account account) {
    return !account.password().equals(formData.get("password"));
  }

  private PageGenerator processRegistraion(String method, Map<String, String> formData) throws IOException {
    if (method.equals("GET")) {
      return processRegistraionGet();
    }

    return processRegistraionPost(formData);
  }

  private RegisterPageGenerator processRegistraionGet() {
    return new RegisterPageGenerator();
  }

  private PageGenerator processRegistraionPost(Map<String, String> formData) throws IOException {
    if (isaBlankInformation(formData)) {
      return new NotEnteredInformationPageGenerator();
    }

    if (isPasswordcheck(formData)) {
      return new PasswordNotEqualsPageGenerator();
    }

    if (isDuplicatedId(formData)) {
      return new DuplicateIdPageGenerator();
    }

    Account account = new Account(
        formData.get("name"),
        formData.get("id"),
        formData.get("password"),
        formData.get("email"));

    accountRepository.accounts().put(account.id(), account);

    accountLoader.write(accountRepository.accounts());

    return new SuccessRegisterPageGenerator();
  }

  private boolean isaBlankInformation(Map<String, String> formData) {
    return formData.get("name") == null ||
        formData.get("id") == null ||
        formData.get("password") == null ||
        formData.get("password-check") == null ||
        formData.get("email") == null;
  }

  private boolean isPasswordcheck(Map<String, String> formData) {
    return !formData.get("password").equals(formData.get("password-check"));
  }

  private boolean isDuplicatedId(Map<String, String> formData) {
    return !(accountRepository.find(formData.get("id")) == null);
  }
}
