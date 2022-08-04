package utils;

import models.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountLoader {

  public Map<String, Account> loadAccount() throws FileNotFoundException {
    Map<String, Account> accounts = new HashMap<>();

    File file = new File("Accounts.csv");

    Scanner scanner = new Scanner(file);
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();

      String[] word = line.split(",");

      String name = word[0];
      String id = word[1];
      String password = word[2];
      String email = word[3];

      Account account = new Account(name,id,password,email);

      accounts.put(account.id(), account);

    }
    return accounts;
  }

  public void write(Map<String, Account> accounts) throws IOException {
    FileWriter fileWriter = new FileWriter("Accounts.csv");

    for(Account account : accounts.values()) {
      String line = account.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }
}
