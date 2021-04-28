package com.liancg.alphasoup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.liancg.alphasoup.service.AlphaSoup;
import com.liancg.alphasoup.service.SoupGenerator;
import com.liancg.alphasoup.service.SoupOptions;
import com.liancg.alphasoup.service.SoupWord;
import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.Tableau;

class SoupGeneratorTest {
  @Test
  void emptySoupIsValid() {
    final SoupOptions options = SoupOptions.allOptions();
    final List<String> wordList = new ArrayList<>();

    final SoupGenerator generator = new SoupGenerator( 15, 20, options, wordList);

    final AlphaSoup soup = generator.generate( 1);

    assertEquals( 15, soup.getWidth());
    assertEquals( 20, soup.getHeight());
    assertEquals( 0, soup.getWords().length);

    Asserts.assertTableauLowercaseLetters( soup.getTableau());
  }

  @Test
  void soupWithSingleWordIsValid() {
    final SoupOptions options = SoupOptions.allOptions();

    final List<String> wordList = new ArrayList<>();
    wordList.add( "sator");

    final SoupGenerator generator = new SoupGenerator( 15, 30, options, wordList);
    final AlphaSoup soup = generator.generate( 1);

    assertEquals( 15, soup.getWidth());
    assertEquals( 30, soup.getHeight());
    assertEquals( 1, soup.getWords().length);

    Asserts.assertTableauLowercaseLetters( soup.getTableau());
  }

  @Test
  void soupWithNonClashingWordsIsValid() {
    final SoupOptions options = SoupOptions.allOptions();
    final List<String> wordList = new ArrayList<>();
    wordList.add( "sator");
    wordList.add( "arepo");
    wordList.add( "tenet");
    wordList.add( "opera");
    wordList.add( "rotas");

    final SoupGenerator generator = new SoupGenerator( 15, 15, options, wordList);

    final List<SoupWord> words = new ArrayList<>();

    final AlphaSoup soup = generator.generate( 5);

    assertEquals( 5, soup.getWords().length);

    assertWords( soup, words);
  }

  private void assertWord( AlphaSoup soup, SoupWord word) {
    final Location[] locations = word.getLetterLocations();
    final Tableau tableau = soup.getTableau();

    for (int i = 0; i < locations.length; i++) {
      final Location location = locations[i];
      final char wordLetter = word.getText().charAt( i);
      final char soupLetter = tableau.getLetter( location);

      assertFalse( soupLetter != ' ' && soupLetter != wordLetter);
    }
  }

  private void assertWords( AlphaSoup soup, List<SoupWord> words) {
    for (final SoupWord word: words) {
      assertWord( soup, word);
    }
  }
}
