import com.sun.net.httpserver.HttpServer;
import models.Account;
import pages.*;
import repositories.AccountRepository;
import services.TransferService;
import utils.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

public class MakaoBankTransactions {
  private final AccountRepository accountRepository;
  private String myIdentifier;

  private TransferService transferService;

  public static void main(String[] args) throws IOException {
    MakaoBankTransactions application = new MakaoBankTransactions();
    application.run();
  }

  public MakaoBankTransactions() {
    accountRepository = new AccountRepository();
    myIdentifier = "110";

    transferService = new TransferService(accountRepository);
  }

  public void run() throws IOException {
    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      URI uri = exchange.getRequestURI();
      String path = uri.getPath();
      String name = path.substring(1);

      String method = exchange.getRequestMethod();

      RequestBodyReader requestBodyReader = new RequestBodyReader(exchange);
      String requestBody = requestBodyReader.body();

      FormParser formParser = new FormParser();
      Map<String, String> formData = formParser.parse(requestBody);

      PageGenerator pageGenerator = process(path, name, method, formData);
      String html = pageGenerator.html();

      exchange.sendResponseHeaders(200, html.getBytes().length);

      new MessageWriter(exchange).write(html);
    });

    httpServer.start();

    System.out.println("Server is listening... http://localhost:8000/");
  }

  public PageGenerator process(
      String path, String name, String method, Map<String, String> formData) {
    String[] steps = path.substring(1).split("/");
    String identifier = steps.length > 1 ? steps[1] : "";

    return switch (steps[0]) {
      case "Account" -> processAccount(identifier);
      case "Transfer" -> processTransfer(method, formData, identifier);
      case "Transactions" -> processTransactions(identifier);
      default -> processGreeting(name);
    };
  }

  public PageGenerator processAccount(String identifier) {
    Account found = accountRepository.find(identifier, myIdentifier);

    return new AccountPageGenerator(found);
  }

  public PageGenerator processTransfer(
      String method, Map<String, String> formData, String identifier) {
    if (method.equals("GET")) {
      return processTransferGet(identifier);
    }

    return processTransferPost(identifier, formData);
  }

  public PageGenerator processTransferGet(String identifier) {
    Account found = accountRepository.find(identifier, myIdentifier);

    return new TransferPageGenerator(found);
  }

  public PageGenerator processTransferPost(
      String identifier, Map<String, String> formData) {
    transferService.transfer(
        (identifier.equals("") ? myIdentifier : identifier),
        formData.get("to"),
        Long.parseLong(formData.get("amount")));

    return new TransferSuccessPageGenerator();
  }

  public PageGenerator processTransactions(String identifier) {
    Account found = accountRepository.find(identifier, myIdentifier);

    return new TransactionsPageGenerator(found);
  }

  public PageGenerator processGreeting(String name) {
    return new GreetingPageGenerator(name);
  }
}
