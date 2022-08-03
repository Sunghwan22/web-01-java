package models;

public class Account {
  private String name;
  private String id;
  private String password;
  private String mail;

  public Account(String name, String id, String password, String mail) {
    this.name = name;
    this.id = id;
    this.password = password;
    this.mail = mail;
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

  public String mail() {
    return mail;
  }
}
