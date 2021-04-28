package com.liancg.alphasoup.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.liancg.alphasoup.service.AlphaSoup;
import com.liancg.alphasoup.service.SoupBuilder;
import com.liancg.alphasoup.service.SoupOptions;
import com.liancg.alphasoup.service.SoupWord;
import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.WordAngle;

class SoupBuilderTest {
  @Test
  void zeroSizeThrowsException() {
    final SoupOptions options = SoupOptions.defaults();

    assertThrows( IllegalArgumentException.class, () -> {
      new SoupBuilder( 0, 0, options);
    });
  }

  @Test
  void zeroWidthThrowsException() {
    final SoupOptions options = SoupOptions.defaults();

    assertThrows( IllegalArgumentException.class, () -> {
      new SoupBuilder( 0, 100, options);
    });
  }

  @Test
  void zeroHeightThrowsException() {
    final SoupOptions options = SoupOptions.defaults();

    assertThrows( IllegalArgumentException.class, () -> {
      new SoupBuilder( 100, 0, options);
    });
  }

  @Test
  void withoutWordsItsFilledWithRandomLetters() {
    final SoupOptions options = SoupOptions.defaults();
    final SoupBuilder builder = new SoupBuilder( 15, 20, options);
    final AlphaSoup soup = builder.build();

    Asserts.assertTableauLowercaseLetters( soup.getTableau());
  }

  @Test
  void wordInIncorrectLocationThrowsException() {
    final SoupOptions options = SoupOptions.allOptions();
    final SoupBuilder builder = new SoupBuilder( 1, 1, options);

    final SoupWord word = new SoupWord( "a", Location.of( 1, 1), WordAngle.EAST);

    assertFalse( builder.wordFits( word));

    assertThrows( IllegalArgumentException.class, () -> {
      builder.addWord( word);
    });
  }

  @Test
  void wordTooLargeThrowsException() {
    final SoupOptions options = SoupOptions.allOptions();
    final SoupBuilder builder = new SoupBuilder( 1, 1, options);

    final SoupWord word = new SoupWord( "aa", Location.of( 0, 0), WordAngle.EAST);

    assertFalse( builder.wordFits( word));

    assertThrows( IllegalArgumentException.class, () -> {
      builder.addWord( word);
    });
  }

  @Test
  void wordBadPlaced1ThrowsException() {
    final SoupOptions options = SoupOptions.allOptions();
    final SoupBuilder builder = new SoupBuilder( 4, 4, options);

    // En la columna derecha, apuntando a la derecha
    final SoupWord word = new SoupWord( "aa", Location.of( 2, 3), WordAngle.EAST);

    assertFalse( builder.wordFits( word));

    assertThrows( IllegalArgumentException.class, () -> {
      builder.addWord( word);
    });
  }

  @Test
  void wordBadPlaced2ThrowsException() {
    final SoupOptions options = SoupOptions.allOptions();
    final SoupBuilder builder = new SoupBuilder( 4, 4, options);

    // En la columna izquierda, apuntando a la izquierda
    final SoupWord word = new SoupWord( "aa", Location.of( 1, 0), WordAngle.WEST);

    assertFalse( builder.wordFits( word));

    assertThrows( IllegalArgumentException.class, () -> {
      builder.addWord( word);
    });
  }

  @Test
  void wordBadPlaced3ThrowsException() {
    final SoupOptions options = SoupOptions.allOptions();
    final SoupBuilder builder = new SoupBuilder( 4, 4, options);

    // En la fila superior, apuntando hacia arriba
    final SoupWord word = new SoupWord( "aa", Location.of( 0, 2), WordAngle.NORTH);

    assertFalse( builder.wordFits( word));

    assertThrows( IllegalArgumentException.class, () -> {
      builder.addWord( word);
    });
  }
}
