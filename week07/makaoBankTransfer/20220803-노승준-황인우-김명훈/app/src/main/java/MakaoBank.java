import com.sun.net.httpserver.HttpServer;
import models.Account;
import repository.AccountRepository;
import services.TransferService;
import utils.AccountPageGenerator;
import utils.FormParser;
import utils.GreetingPageGenerator;
import utils.PageGenerator;
import utils.MessageWriter;
import utils.RequestBodyReader;
import utils.TransferPageGenerator;
import utils.TransferSucessPageGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MakaoBank {
  private TransferService transferService;
  private String myIdentifier = "1234";
  private AccountRepository accountRepository;

  public static void main(String[] args) throws IOException {
    MakaoBank application = new MakaoBank();
    application.run();
  }

  private void run() throws IOException {
    accountRepository = new AccountRepository();

    transferService = new TransferService(accountRepository);

    InetSocketAddress address = new InetSocketAddress(8000);

    HttpServer httpServer = HttpServer.create(address, 0);

    httpServer.createContext("/", (exchange) -> {
      // 1. 입려 확인
      URI requestURI = exchange.getRequestURI();
      String path = requestURI.getPath();

      String method = exchange.getRequestMethod();

      RequestBodyReader requestBodyReader = new RequestBodyReader(exchange);
      String requestBody = requestBodyReader.body();

      FormParser formParser = new FormParser();
      Map<String, String> formData = formParser.parse(requestBody);

      // 2. 처리
      PageGenerator pageGenerator = process(path, method, formData);

      String content = pageGenerator.html();

      // 3. 출력
      MessageWriter messageWriter = new MessageWriter(exchange);
      messageWriter.write(content);
    });

    httpServer.start();
    System.out.println("Server is Starting... " + "http://localhost:8000");
  }

  private PageGenerator process(String path,
                                String method,
                                Map<String, String> formData) {
    String[] steps = path.substring(1).split("/");

    return switch (steps[0]) {
      case "account" -> processAccount(steps.length > 1 ? steps[1] : "");
      case "transfer" -> processTransfer(method, formData);
      default -> new GreetingPageGenerator();
    };
  }

  private PageGenerator processAccount(String identifier) {
    Account found = accountRepository.find(identifier, myIdentifier);
    return new AccountPageGenerator(found);
  }

  private PageGenerator processTransfer(String method, Map<String, String> formData) {
    if (method.equals("GET")) {
      return processTransferGet();
    }

    return prcessTransferPost(formData);
  }

  private TransferPageGenerator processTransferGet() {
    Account found = accountRepository.find(myIdentifier);

    return new TransferPageGenerator(found);
  }

  private TransferSucessPageGenerator prcessTransferPost(
      Map<String, String> formData) {
    transferService.transfer(
        myIdentifier,
        formData.get("to"),
        Long.parseLong(formData.get("amount")));

    Account account = accountRepository.find(myIdentifier);
    return new TransferSucessPageGenerator(account);
  }
}
