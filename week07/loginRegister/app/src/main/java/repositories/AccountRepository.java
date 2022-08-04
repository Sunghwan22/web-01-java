package repositories;

import models.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
  private final Map<String, Account> accounts = new HashMap<>();

  public Map<String, Account> accounts() {
    return accounts;
  }

  public Account find(String id) {
    return  accounts.get(id);
  }
}
