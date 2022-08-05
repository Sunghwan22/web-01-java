package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierManagerTest {
  @Test
  void setInitialIdentifierCount() {
    IdentifierManager identifierManager = new IdentifierManager();

    identifierManager.setInitialIdentifierCount(7);

    assertEquals(7, identifierManager.identifierCount());
  }

  @Test
  void assignIdentifier() {
    IdentifierManager identifierManager = new IdentifierManager();

    identifierManager.setInitialIdentifierCount(5);

    int identifier = identifierManager.assignIdentifier();

    assertEquals(6, identifier);
    assertEquals(6, identifierManager.identifierCount());
  }
}