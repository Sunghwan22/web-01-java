package utils;

import models.User;
import repositories.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileLoader {
  public UserRepository loadData(String path) throws FileNotFoundException {
    File file = new File(path);

    Scanner scanner = new Scanner(file);

    UserRepository userRepository = new UserRepository();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      String[] formData = line.split(",");

      String name = formData[0];
      String id = formData[1];
      String password = formData[2];
      String email = formData[3];

      User user = new User(name, id, password, email);

      userRepository.addUser(user);
    }

    return userRepository;
  }

  public void saveData(
      String path, UserRepository userRepository) throws IOException {
    FileWriter fileWriter = new FileWriter(path);

    userRepository.users().forEach(
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
