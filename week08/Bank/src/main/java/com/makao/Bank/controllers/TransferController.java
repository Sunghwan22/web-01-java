package com.makao.Bank.controllers;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import com.makao.Bank.services.TransferService;
import com.makao.Bank.views.AccountNotFoundPageGenerator;
import com.makao.Bank.views.PageGenerator;
import com.makao.Bank.views.TransferPageGenerator;
import com.makao.Bank.views.TransferSuccessPageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
  private TransferService transferService;

  public TransferController(TransferService transferService) {
    this.transferService = transferService;
  }

  @GetMapping("/transfer")
  public String transferPage() {
    PageGenerator pageGenerator = new TransferPageGenerator();
    return pageGenerator.html();
  }

// todo 인풋으로 얻은 두개 의 값 얻기 account가 잔액이 변해야 함

  @PostMapping("/transfer")
  public String transfer(
      @RequestParam("to") String to,
      @RequestParam("amount") long amount
   ) {
    Account receiver = transferService.transfer("1234", to, amount);

    PageGenerator pageGenerator = receiver == null
        ? new AccountNotFoundPageGenerator()
        : new TransferSuccessPageGenerator();

    return pageGenerator.html();
  }
}
