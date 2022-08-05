package repositories;

import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserRepository {
  private Map<String, User> users = new HashMap<>();

  public void loadData(String path) throws FileNotFoundException {
    File file = new File(path);

    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      String[] formData = line.split(",");

      String name = formData[0];
      String id = formData[1];
      String password = formData[2];
      String email = formData[3];

      User user = new User(name, id, password, email);

      users.put(id, user);
    }
  }

  public void saveData(String path) throws IOException {
    FileWriter fileWriter = new FileWriter(path);

    users.forEach(
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

  public User findUser(String id) {
    return users.get(id);
  }

  public void addUser(User user) {
    users.put(user.id(), user);
  }
}
