package models;

public class Account {
  private String name;
  private String id;
  private String password;
  private String email;

  public Account(String name, String id, String password, String email) {

    this.name = name;
    this.id = id;
    this.password = password;
    this.email = email;
  }
}
