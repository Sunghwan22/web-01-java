package pages;

public class LoginSuccessPageGenerator extends PageGenerator{
  @Override
  public String content() {
    // Todo 메가에는 유저의 이름이 들어가야 함 account를 받아야함

    return "<h1>마카오 뱅크.</h1>\n" +
        "<p>안녕하세요, " + "메가" + "님! 행복을 적립하는 마카오뱅크입니다.\n";
  }
}
