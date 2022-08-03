package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterPageGeneratorTest {
  @Test
  void content() {
    PageGenerator pageGenerator = new RegisterPageGenerator();

    String html = pageGenerator.html();

    assertTrue(html.contains("<h1>회원가입"), "회원가입 메시지 문제 " + html);
    assertTrue(html.contains("이름"), "회원가입 메시지 문제 " + html);
    assertTrue(html.contains("아이디"), "회원가입 메시지 문제 " + html);
    assertTrue(html.contains("비밀번호"), "회원가입 메시지 문제 " + html);
    assertTrue(html.contains("<input type=\"text"), "입력칸 문제 " + html);
    assertTrue(html.contains("<button type=\"submit\">회원가입"),
        "회원가입 버튼 문제 " + html);

  }
}
