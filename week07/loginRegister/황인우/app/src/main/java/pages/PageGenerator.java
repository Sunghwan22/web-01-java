package pages;

public abstract class PageGenerator {
  public String html() {
    return "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "<meta charset=\"UTF-8\" />\n" +
        "<title>Login/Register</title>\n" +
        styles() +
        "</head>\n" +
        "<body>\n" +
        content() +
        "</body>\n" +
        "</html>";
  }

  public String styles() {
    return "<style>\n" +
        "a {\n" +
        "padding: 5px 10px;\n" +
        "border: 1px solid black;\n" +
        "border-radius: 5px;\n" +
        "}\n" +
        "label {\n" +
        "display: block;\n" +
        "}\n" +
        ".notification {\n" +
        "font-size: 2em;\n" +
        "font-weight: bold;\n" +
        "}\n" +
        "</style>\n";
  }

  public abstract String content();
}
