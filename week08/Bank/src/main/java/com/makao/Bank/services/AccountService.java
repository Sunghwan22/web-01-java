package com.makao.Bank.services;

import com.makao.Bank.models.Account;
import com.makao.Bank.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  private final AccountRepository acccountRepository;

  public AccountService(AccountRepository acccountRepository) {
    this.acccountRepository = acccountRepository;
  }

  public Account myAccount() {
    Account account = acccountRepository.find("1234");
    return account;
  }
}
