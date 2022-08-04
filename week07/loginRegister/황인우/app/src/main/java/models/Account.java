package models;

public class Account {
  private final String name;
  private final String id;
  private final String password;
  private final String email;

  public Account(String name, String id, String password, String email) {
    this.name = name;
    this.id = id;
    this.password = password;
    this.email = email;
  }

  public String name() {
    return name;
  }

  public String id() {
    return id;
  }

  public String password() {
    return password;
  }

  public String email() {
    return email;
  }
}
