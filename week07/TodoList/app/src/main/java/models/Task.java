package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
  public final static String PROCESSING = "PROCESSING";

  private String title;
  private String timeStamp;
  private String state;

  public Task(String text) {
    this.title = text;
    this.timeStamp = stamping();
    this.state = PROCESSING;
  }

  public Task(String title, String timeStamp, String state) {
    this.title = title;
    this.timeStamp = timeStamp;
    this.state = state;
  }

  public String title() {
    return title;
  }

  public String timeStamp() {
    return timeStamp;
  }

  public String state() {
    return state;
  }

  public String stamping() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    return currentDateTime.format(formatter);
  }

  public String csvRow() {
    return String.join(",", this.title(), this.timeStamp, this.state);
  }
}