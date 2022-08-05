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
  private final Map<String, User> users = new HashMap<>();

  public UserRepository() throws FileNotFoundException {
    File file = new File("/Users/seonghwan/Desktop/web01java/week07/loginRegister/Users.csv");

    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      String[] word = line.split(",");

      String name = word[0];
      String id = word[1];
      String password = word[2];
      String email = word[3];

      User user = new User(name, id, password, email);

      users.put(user.id(), user);
    }
  }

  public User find(String id) {
    return users.get(id);
  }

  public Map<String, User> users() {
    return users;
  }

  public void write(Map<String, User> users) throws IOException {
    FileWriter fileWriter = new FileWriter("/Users/seonghwan/Desktop/web01java/week07/loginRegister/Users.csv");

    for (User user : users.values()) {
      String line = user.toCsvRow();
      fileWriter.write(line + "\n");
    }
    fileWriter.close();
  }
}
