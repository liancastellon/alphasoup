package com.liancg.alphasoup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.WordAngle;

public class SoupGenerator {
  private final SoupBuilder builder;
  private final int width;
  private final int height;
  private final List<String> wordList;
  private final List<String> wordsAdded = new ArrayList<>();

  private final Random rand = new Random();

  public SoupGenerator( int width, int height, SoupOptions options, List<String> wordList) {
    this.width = width;
    this.height = height;
    this.wordList = wordList;

    builder = new SoupBuilder( width, height, options);
  }

  public AlphaSoup generate( int maxWordCount) {
    if (wordList.size() > 0) {
      for (int i = 0; i < maxWordCount; i++) {
        addWord( pickRandomWord(), pickRandomLocation(), pickRandomAngle());
      }
    }

    return builder.build();
  }

  private void addWord( String text, Location location, WordAngle angle) {
    addWord( new SoupWord( text, location, angle));
  }

  private void addWord( SoupWord word) {
    if (!wordsAdded.contains( word.getText())) {
      word = tryWithAllAngles( word);

      while (!builder.wordFits( word)) {
        int row = 0;

        while (row < height && !builder.wordFits( word)) {
          int column = 0;

          while (column < width && !builder.wordFits( word)) {
            word = word.moveTo( Location.of( row, column));

            if (!builder.wordFits( word)) {
              word = tryWithAllAngles( word);
            }

            column++;
          }

          row++;
        }
      }

      if (!builder.wordFits( word)) {
        throw new IllegalArgumentException( "La palabra no se puede ubicar en la sopa");
      }

      builder.addWord( word);

      wordsAdded.add( word.getText());
    }
  }

  private SoupWord tryWithAllAngles( SoupWord word) {
    if (!builder.wordFits( word)) {
      // Primero la rotamos a ver si cabe
      final WordAngle originalAngle = word.getAngle();
      WordAngle angle = originalAngle.next();

      word = word.changeAngle( angle);

      while (angle != originalAngle && !builder.wordFits( word)) {
        angle = angle.next();
        word = word.changeAngle( angle);
      }
    }

    return word;
  }

  private String pickRandomWord() {
    return wordList.get( rand.nextInt( wordList.size()));
  }

  private Location pickRandomLocation() {
    final int row = rand.nextInt( width);
    final int column = rand.nextInt( height);

    return Location.of( row, column);
  }

  private WordAngle pickRandomAngle() {
    final WordAngle[] angles = WordAngle.values();

    return angles[rand.nextInt( angles.length)];
  }
}
