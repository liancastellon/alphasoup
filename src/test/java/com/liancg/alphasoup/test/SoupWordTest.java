package com.liancg.alphasoup.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.liancg.alphasoup.service.AlphaSoup;
import com.liancg.alphasoup.service.SoupWord;
import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.Tableau;
import com.liancg.alphasoup.utils.WordAngle;

class SoupWordTest {
  private static String LETTERS = "abcdefghijklmnopqrstuvwxyz";
  private final Random rand = new Random();

  @Test
  void constructorAcceptsValidsData() {
    final Tableau tableau = randomTableau( 15, 20);
    final SoupWord[] words = randomWords( 15, 20, 10);

    final AlphaSoup soup = new AlphaSoup( 15, 20, tableau, words);

    assertEquals( 15, soup.getWidth());
    assertEquals( 20, soup.getHeight());
    assertArrayEquals( words, soup.getWords());

    Asserts.assertTableauEquals( tableau, soup.getTableau());
  }

  @Test
  void constructorThrowsWithInvalidSizes() {
    final Tableau tableau = randomTableau( 15, 20);
    final SoupWord[] words = randomWords( 15, 20, 10);

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( 0, 0, tableau, words);
    });

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( 15, 0, tableau, words);
    });

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( -1, 10, tableau, words);
    });

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( 15, -1, tableau, words);
    });
  }

  @Test
  void constructorThrowsWithNullTableau() {
    final SoupWord[] words = randomWords( 15, 20, 10);

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( 15, 20, null, words);
    });
  }

  @Test
  void constructorThrowsWithEmptyTableau() {
    final Tableau tableau = new Tableau( 0, 0);
    final SoupWord[] words = randomWords( 15, 20, 10);

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( 15, 20, tableau, words);
    });
  }

  @Test
  void constructorThrowsWithNullWords() {
    final Tableau tableau = randomTableau( 15, 20);

    assertThrows( IllegalArgumentException.class, () -> {
      new AlphaSoup( 15, 20, tableau, null);
    });
  }

  private Tableau randomTableau( int width, int height) {
    final Tableau result = new Tableau( width, height);

    for (int row = 0; row < result.getHeight(); row++) {
      for (int column = 0; column < result.getWidth(); column++) {
        result.setLetter( row, column, randomLetter());
      }

    }

    return result;
  }

  private SoupWord[] randomWords( int width, int height, int count) {
    final List<SoupWord> words = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      words.add( randomWord( width, height));
    }

    return words.toArray( new SoupWord[0]);
  }

  private char randomLetter() {
    return LETTERS.charAt( rand.nextInt( LETTERS.length()));
  }

  private SoupWord randomWord( int width, int height) {
    final int length = rand.nextInt( Math.max( width, height)) + 2;
    final String text = randomText( length);

    final Location location = randomLocation( height, width);
    final WordAngle angle = randomAngle();

    return new SoupWord( text, location, angle);
  }

  private Location randomLocation( int height, int width) {
    final int row = rand.nextInt( height);
    final int column = rand.nextInt( width);

    return Location.of( row, column);
  }

  private String randomText( int length) {
    final StringBuilder sb = new StringBuilder();

    for (int i = 0; i < length; i++) {
      sb.append( randomLetter());
    }

    return sb.toString();
  }

  private WordAngle randomAngle() {
    final WordAngle[] values = WordAngle.values();

    return values[rand.nextInt( values.length)];
  }
}
