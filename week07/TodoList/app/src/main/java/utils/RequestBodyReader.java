package utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;
import java.util.Scanner;

public class RequestBodyReader {
  private HttpExchange exchange;

  public RequestBodyReader(HttpExchange exchange) {
    this.exchange = exchange;
  }

  public String body() {
    InputStream inputStream = exchange.getRequestBody();

    Scanner scanner = new Scanner(inputStream);

    if (!scanner.hasNextLine()) {
      return "";
    }

    String line = scanner.nextLine();

    String[] words = line.split("=");

    return words[1];
  }
}