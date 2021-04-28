package com.liancg.alphasoup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.liancg.alphasoup.utils.Tableau;

class TableauTest {
  @Test
  void createdWithSizeZero() {
    final Tableau tableau = new Tableau( 0, 0);

    assertEquals( 0, tableau.getWidth());
    assertEquals( 0, tableau.getHeight());
    assertTrue( tableau.isEmpty());
  }

  @Test
  void createdWithWidthZero() {
    final Tableau tableau = new Tableau( 0, 10);

    assertEquals( 0, tableau.getWidth());
    assertEquals( 10, tableau.getHeight());
    assertTrue( tableau.isEmpty());
  }

  @Test
  void createdWithHeightZero() {
    final Tableau tableau = new Tableau( 10, 0);

    assertEquals( 10, tableau.getWidth());
    assertEquals( 0, tableau.getHeight());
    assertTrue( tableau.isEmpty());
  }

  @Test
  void createdWithCorrectValues() {
    final Tableau tableau = new Tableau( 10, 20);

    assertEquals( 10, tableau.getWidth());
    assertEquals( 20, tableau.getHeight());

    Asserts.assertTableauClean( tableau);
  }

  @Test
  void fillingWithRandomLettersWorksOk() {
    final Tableau tableau = new Tableau( 10, 20);
    tableau.fillWithRandomLetters();

    Asserts.assertTableauLowercaseLetters( tableau);
  }

  @Test
  void letterIsSetCorrectly() {
    final Tableau tableau = new Tableau( 10, 20);

    tableau.setLetter( 0, 0, 'A');
    tableau.setLetter( 3, 5, 'B');

    assertEquals( 'A', tableau.getLetter( 0, 0));
    assertEquals( 'B', tableau.getLetter( 3, 5));
  }
}
