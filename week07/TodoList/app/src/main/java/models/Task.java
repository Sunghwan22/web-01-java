package models;

public class Task {
  private final String content;

  public Task(String content) {
    this.content = content;
  }

  public String content() {
    return content;
  }

  public String toCsvRow() {
    return content;
  }
}
