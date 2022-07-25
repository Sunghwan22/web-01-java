package models;

public class TransactionResult {
  private Transaction transaction;
  private int amount;

  public TransactionResult(Transaction transaction, int amount) {
    this.transaction = transaction;
    this.amount = amount;
  }

  @Override
  public boolean equals(Object other) {
    TransactionResult otherTransactionResult = (TransactionResult) other;
    return this.transaction == otherTransactionResult.transaction
        && this.amount == otherTransactionResult.amount;
  }

  @Override
  public String toString() {
    return "TransactionResult(" +
        transaction +
        "," + amount + ")";
  }

  private String[] values() {
    return new String[] {transaction.type(),
        transaction.amount().toString(),
        Integer.toString(amount)};
  }

  public String toCsvRow() {
    return String.join(",",values());
  }
}