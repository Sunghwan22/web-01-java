package models;

public class Task {
  private final int identifier;
  private final String content;

  public Task(int identifier, String content) {
    this.identifier = identifier;
    this.content = content;
  }

  public int identifier() {
    return identifier;
  }

  public String content() {
    return content;
  }
}
