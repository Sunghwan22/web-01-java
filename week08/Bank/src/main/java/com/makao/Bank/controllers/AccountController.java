package com.makao.Bank.controllers;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import com.makao.Bank.services.AccountService;
import com.makao.Bank.views.AccountPageGenerator;
import com.makao.Bank.views.PageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
  private AccountService accountService;

  public AccountController(AccountService accountService) {
     this.accountService = accountService;
  }

  @GetMapping("/account")
  public String account() {
    Account account = accountService.myAccount();

    PageGenerator pageGenerator = new AccountPageGenerator(account);

    return pageGenerator.html();
  }
}
