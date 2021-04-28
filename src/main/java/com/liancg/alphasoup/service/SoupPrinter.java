package com.liancg.alphasoup.service;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.Tableau;

@Service
public class SoupPrinter {
  public void print( AlphaSoup soup, PrintStream printStream) {
    printWithHighlight( soup, soup.getFoundWords(), false, printStream);
  }

  public void printWithWords( AlphaSoup soup, PrintStream printStream) {
    printWithHighlight( soup, soup.getWords(), false, printStream);
  }

  public void printWithWordsAndBlanks( AlphaSoup soup, PrintStream printStream) {
    printWithHighlight( soup, soup.getWords(), true, printStream);
  }

  public void printWithHighlight( AlphaSoup soup, SoupWord[] words, boolean showBlanks,
    PrintStream printStream) {
    final Tableau tableau = soup.getTableau();

    for (int row = 0; row < tableau.getHeight(); row++) {
      for (int column = 0; column < tableau.getWidth(); column++) {
        char letter = tableau.getLetter( row, column);

        if (locationInAnyWord( row, column, words)) {
          letter = Character.toUpperCase( letter);
        }
        else if (showBlanks) {
          letter = ' ';
        }

        printStream.print( letter);

        if (column <= tableau.getWidth() - 1) {
          printStream.print( "|");
        }

      }

      printStream.println( "");
    }
  }

  private boolean locationInAnyWord( int row, int column, SoupWord[] words) {
    for (final SoupWord word: words) {
      final Location[] locations = word.getLetterLocations();

      for (final Location location: locations) {
        if (location.getRow() == row && location.getColumn() == column) {
          return true;
        }
      }
    }

    return false;
  }
}
