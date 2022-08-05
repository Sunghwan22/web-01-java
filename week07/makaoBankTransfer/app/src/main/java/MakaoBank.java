import com.sun.net.httpserver.*;
import models.*;
import services.*;
import utils.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class MakaoBank {
  private Account account;
  private TransferService transferService;
  private FormParser formParser;
  private  List<Account> accounts;
  public static void main(String[] args) throws IOException {
    MakaoBank application = new MakaoBank();
    application.run();
  }

  public MakaoBank() {
    this.formParser = formParser;
  }

  private void run() throws IOException {
        accounts = List.of(
        new Account("1234", "Ashal", 3000),
        new Account("2345", "Joker", 1000)
    );
    account = accounts.get(0);
    transferService = new TransferService(accounts);

    InetSocketAddress address = new InetSocketAddress(8000);
    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", exchange -> {
      URI requestURI = exchange.getRequestURI();
      String path = requestURI.getPath();
      String method = exchange.getRequestMethod();

      RequestBodyReader requestBodyReader = new RequestBodyReader(exchange);

      String requestBody = requestBodyReader.body();




      Map<String,String> formData = formParser.parse(requestBody);


      PageGenerator pageGenerator = process(path, method,formData);

      String content = pageGenerator.html();

      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });

    System.out.print("Server is Listening .. " + "http://localhost:8000");
    httpServer.start();
  }

  private PageGenerator process(String path, String method,Map<String,String> formData) {
    String[] steps = path.substring(1).split("/");
    return switch (steps[0]) {
      case "/account" -> processAccount(steps.length > 1 ? steps[1] :  "");
      case "/transfer" -> processTransfer(method,formData);
      default -> new GreetingPageGenerator();
    };
  }

  private AccountPageGenerator processAccount(String identifier) {

    Account found = accounts.stream()
        .filter(account -> account.identifier().equals(identifier))
        .findFirst().
        orElse(account);
    return new AccountPageGenerator(found);
  }

  private PageGenerator processTransfer(String method,
                                        Map<String, String> formData) {
    if (method.equals("GET")) {
      return processTransferGet();
    }
      return processTransferPost(formData);
  }

  private TransferPageGenerator processTransferGet() {
    return new TransferPageGenerator(account);
  }

  private TransferSuccessPageGenerator processTransferPost(Map<String,String> formData) {

    transferService.transfer(account.identifier(), formData.get("to"),(int) Long.parseLong(formData.get("amount")));
    return new TransferSuccessPageGenerator(account);
  }
}