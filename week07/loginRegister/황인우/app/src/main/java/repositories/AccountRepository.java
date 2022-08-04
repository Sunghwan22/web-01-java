package repositories;

import models.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
  private Map<String, Account> accounts = new HashMap<>();

  public Map<String, Account> accounts() {
    return new HashMap<>(accounts);
  }

  public Account findAccount(String id) {
    return accounts.get(id);
  }

  public void addAccount(Account account) {
    accounts.put(account.id(), account);
  }
}
