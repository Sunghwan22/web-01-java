package pages;

import models.Account;
import org.junit.jupiter.api.Test;
import repositories.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;

class LoginSuccessPageGeneratorTest {
  @Test
  void content() {
    AccountRepository accountRepository = new AccountRepository();

    Account account
        = new Account("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com");

    PageGenerator pageGenerator = new LoginSuccessPageGenerator(account);

    String content = pageGenerator.content();

    assertTrue(
        content.contains("마카오 뱅크"),
        "로그인 성공 페이지 제목 오류\n" + content);
    assertTrue(
        content.contains("안녕하세요 황인우 님, 행복을 적립하는 마카오뱅크입니다."),
        "로그인 성공 페이지 인사말 오류\n" + content);
  }
}
