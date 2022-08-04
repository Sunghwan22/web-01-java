package repositories;

import models.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
  private Map<String, Account> accounts = new HashMap<>();

  public AccountRepository() {
    accounts.putAll(Map.of(
        "hsjkdss228", new Account("황인우", "hsjkdss228", "dlsdn12", "hsjkdss228@naver.com"),
        "dhkddlsgn228", new Account("김인우", "dhkddlsgn228", "dlsdn12", "dhkddlsgn228@gmail.com"),
        "innu3368", new Account("이인우", "innu3368", "dlsdn12", "innu3368@instagram.com")
    ));
  }

  public Account findAccount(String id) {
    return accounts.get(id);
  }

  public void addAccount(Account account) {
    accounts.put(account.id(), account);
  }
}
