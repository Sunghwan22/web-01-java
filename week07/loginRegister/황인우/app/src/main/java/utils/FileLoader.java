package utils;

import models.Account;
import repositories.AccountRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileLoader {
  public AccountRepository loadData(String path) throws FileNotFoundException {
    File file = new File(path);

    Scanner scanner = new Scanner(file);

    AccountRepository accountRepository = new AccountRepository();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      String[] formData = line.split(",");

      String name = formData[0];
      String id = formData[1];
      String password = formData[2];
      String email = formData[3];

      Account account = new Account(name, id, password, email);

      accountRepository.addAccount(account);
    }

    return accountRepository;
  }

  public void saveData(
      String path, AccountRepository accountRepository) throws IOException {
    FileWriter fileWriter = new FileWriter(path);

    accountRepository.accounts().forEach(
        (id, account) -> {
          try {
            fileWriter.write(
                account.name() + "," + account.id() + ","
                    + account.password() + "," + account.email() + "\n");
          } catch (IOException exception) {
            throw new RuntimeException(exception);
          }
        }
    );

    fileWriter.close();
  }
}
