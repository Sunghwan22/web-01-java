package repositories;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
  private Map<String, User> users = new HashMap<>();

  public Map<String, User> users() {
    return new HashMap<>(users);
  }

  public User findUser(String id) {
    return users.get(id);
  }

  public void addUser(User user) {
    users.put(user.id(), user);
  }
}
