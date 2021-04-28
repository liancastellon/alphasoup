package com.liancg.alphasoup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.liancg.alphasoup.utils.Tableau;

public class Asserts {
  public static void assertTableauLowercaseLetters( Tableau tableau) {
    for (int row = 0; row < tableau.getHeight(); row++) {
      for (int column = 0; column < tableau.getWidth(); column++) {
        final char letter = tableau.getLetter( row, column);

        assertTrue( letter >= 'a' && letter <= 'z');
      }
    }
  }

  public static void assertTableauClean( Tableau tableau) {
    for (int row = 0; row < tableau.getHeight(); row++) {
      for (int column = 0; column < tableau.getWidth(); column++) {
        assertEquals( ' ', tableau.getLetter( row, column));
      }
    }
  }

  public static void assertTableauEquals( Tableau tableau1, Tableau tableau2) {
    assertEquals( tableau1.getWidth(), tableau2.getWidth());
    assertEquals( tableau1.getHeight(), tableau2.getHeight());

    for (int row = 0; row < tableau1.getHeight(); row++) {
      for (int column = 0; column < tableau1.getWidth(); column++) {
        final char letter1 = tableau1.getLetter( row, column);
        final char letter2 = tableau2.getLetter( row, column);

        assertEquals( letter1, letter2);
      }
    }
  }
}
