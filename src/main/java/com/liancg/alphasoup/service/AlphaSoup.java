package com.liancg.alphasoup.service;

import java.util.ArrayList;
import java.util.List;

import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.Tableau;

public class AlphaSoup {
  private final int width;
  private final int height;
  private final Tableau tableau;
  private final SoupWord[] words;
  private final List<SoupWord> foundWords = new ArrayList<>();

  public AlphaSoup( int width, int height, Tableau tableau, SoupWord[] words) {
    if (width < 15 || height < 15 || width > 80 || height > 80) {
      throw new IllegalArgumentException(
        "Ancho/alto no tienen las dimensiones correctas (entre 15 y 80)");
    }

    if (tableau == null || tableau.isEmpty()) {
      throw new IllegalArgumentException( "Tablero de letras es nulo o vacio");
    }

    if (words == null) {
      throw new IllegalArgumentException( "Arreglo de palabras es nulo");
    }

    this.width = width;
    this.height = height;
    this.tableau = tableau;
    this.words = words;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Tableau getTableau() {
    return tableau;
  }

  public SoupWord[] getWords() {
    return words;
  }

  public SoupWord[] getFoundWords() {
    return foundWords.toArray( new SoupWord[0]);
  }

  public char letterAt( int row, int col) {
    return tableau.getLetter( row, col);
  }

  public boolean hasWordAt( String text, Location startLocation, Location endLocation) {
    final SoupWord word = findWordWithText( text.toLowerCase());

    return word != null && startLocation.equals( word.getLocation())
      && endLocation.equals( word.getEndLocation());
  }

  private SoupWord findWordWithText( String text) {
    int i = 0;

    while (i < words.length && !words[i].getText().equals( text)) {
      i++;
    }

    return i < words.length ? words[i] : null;
  }

  public boolean selectWord( Location startLocation, Location endLocation) {
    final SoupWord word = findWordByLocations( startLocation, endLocation);

    if (word != null && !foundWords.contains( word)) {
      foundWords.add( word);
    }

    return word != null;
  }

  private SoupWord findWordByLocations( Location startLocation, Location endLocation) {
    for (final SoupWord word: words) {
      if (word.getLocation().equals( startLocation) && word.getEndLocation().equals( endLocation)) {
        return word;
      }
    }

    return null;
  }

}
