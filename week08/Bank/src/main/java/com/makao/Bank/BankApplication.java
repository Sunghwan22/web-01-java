// http://localhost:8080/
// Controller -> 특정 path에 대한 처리 방식을 결정한다
// HomeController -> Get (/)로 시작하는

package com.makao.Bank;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {
  // 다른컨트롤러에서 받아오는 레포지토리가 전부다 new를 해줘서 다 다른거임 같은걸로 해줘야하기 떄문에 의존성 주입이 필요함
  private AccountRepository accountRepository;

  public static void main(String[] args) {
    SpringApplication.run(BankApplication.class, args);
  }
}
