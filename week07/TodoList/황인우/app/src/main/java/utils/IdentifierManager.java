package utils;

public class IdentifierManager {
  private static int identifierCount;

  public void setInitialIdentifierCount(int identifierCount) {
    IdentifierManager.identifierCount = identifierCount;
  }

  public int identifierCount() {
    return IdentifierManager.identifierCount;
  }

  public int assignIdentifier() {
    IdentifierManager.identifierCount += 1;

    return IdentifierManager.identifierCount;
  }
}
