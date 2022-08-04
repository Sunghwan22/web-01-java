package repositories;

import models.Account;
import utils.AccountLoader;

import java.io.FileNotFoundException;
import java.util.Map;

public class AccountRepository {
  private final Map<String, Account> accounts;

  public AccountRepository() throws FileNotFoundException {
      AccountLoader accountLoader = new AccountLoader();
      accounts = accountLoader.loadAccount();
  }

  public Map<String, Account> accounts() {
    return accounts;
  }

  public Account find(String id) {
    return  accounts.get(id);
  }
}
