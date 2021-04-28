package com.liancg.alphasoup.service;

import java.util.ArrayList;
import java.util.List;

import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.Tableau;
import com.liancg.alphasoup.utils.WordAngle;

public class SoupBuilder {
  private final int width;
  private final int height;
  private final Tableau tableau;

  private final List<SoupWord> words = new ArrayList<>();
  private final SoupOptions options;

  public SoupBuilder( int width, int height, SoupOptions options) {
    if (width == 0 || height == 0) {
      throw new IllegalArgumentException( "Ancho/alto es CERO");
    }

    if (options == null) {
      throw new IllegalArgumentException( "Options es null");
    }

    this.width = width;
    this.height = height;
    this.options = options;

    tableau = new Tableau( width, height);
  }

  public SoupBuilder addWord( String text, Location location, WordAngle angle) {
    return addWord( new SoupWord( text, location, angle));
  }

  public SoupBuilder addWord( SoupWord word) {
    if (!wordFits( word)) {
      throw new IllegalArgumentException( "La palabra no puede ser adicionada en esta posicion");
    }

    words.add( word);
    addWordToSoup( word);

    return this;
  }

  public boolean wordFits( SoupWord word) {
    if (options.allowsAngle( word.getAngle())) {
      Location location = word.getLocation();
      boolean hasValidLocation = location.getRow() < height && location.getColumn() < width;
      boolean hasValidLength = word.getLength() <= height && word.getLength() <= width;

      Location endLocation = word.getEndLocation();

      boolean hasValidOrientation = endLocation.getRow() >= 0 && endLocation.getRow() <= height
        && endLocation.getColumn() >= 0 && endLocation.getColumn() < width;

      return hasValidLocation && hasValidLength && hasValidOrientation
        && !interceptsAnotherWord( word);
    }
    else {
      return false;
    }

  }

  public AlphaSoup build() {
    tableau.fillWithRandomLetters();

    return new AlphaSoup( width, height, tableau, words.toArray( new SoupWord[0]));
  }

  private boolean interceptsAnotherWord( SoupWord word) {
    Location[] locations = word.getLetterLocations();

    for (int i = 0; i < locations.length; i++) {
      Location location = locations[i];
      char wordLetter = word.getText().charAt( i);
      char soupLetter = tableau.getLetter( location);

      if (soupLetter != ' ' && soupLetter != wordLetter) {
        return true;
      }

    }

    return false;
  }

  private void addWordToSoup( SoupWord word) {
    Location[] locations = word.getLetterLocations();

    for (int i = 0; i < locations.length; i++) {
      Location location = locations[i];
      char wordLetter = word.getText().charAt( i);

      tableau.setLetter( location, wordLetter);
    }

  }
}
