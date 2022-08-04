package pages;

public abstract class PageGenerator {
  public String html() {
    return "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "<meta charset=\"UTF-8\" />\n" +
        "<title>뫄콰오 뵁쿠</title>\n" +
        "</head>\n" +
        "<body>\n" +
        navigation() +
        content() +
        "</body>\n" +
        "</html>";
  }

  public String navigation() {
    return "<a href=\"/\">홈으로</a> \n" +
        "<a href=\"/account\">잔액 조회</a> \n" +
        "<a href=\"/transfer\">송금</a>\n";
  }

  public abstract String content();
}
