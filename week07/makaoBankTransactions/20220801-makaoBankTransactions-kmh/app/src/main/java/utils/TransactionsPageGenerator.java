package utils;

import models.Account;

import java.util.stream.Collectors;

public class TransactionsPageGenerator extends PageGenerator {
  private final Account account;

  public TransactionsPageGenerator(Account account) {
    super();

    this.account = account;
  }

  @Override
  public String content() {
    return "<h1>거래 내역</h1>" +
        account.transactions().stream()
            .map(transaction -> "<p>" +
                transaction.command(account) + ": " +
                transaction.other(account).name() + " " +
                transaction.amount() + "원" +
                "</p>\n")
            .collect(Collectors.joining());
  }
}