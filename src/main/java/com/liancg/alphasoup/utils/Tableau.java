package com.liancg.alphasoup.utils;

import java.util.Random;

public class Tableau {
  private static String LETTERS = "abcdefghijklmnopqrstuvwxyz";

  private final Random rand = new Random();

  private final int width;
  private final int height;
  private char[][] letters;

  public Tableau( int width, int height) {
    this.width = width;
    this.height = height;

    initializeLetters();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public boolean isEmpty() {
    return letters == null || height == 0 || width == 0;
  }

  public char getLetter( int row, int col) {
    return letters[row][col];
  }

  public char getLetter( Location location) {
    return getLetter( location.getRow(), location.getColumn());
  }

  public void setLetter( Location location, char letter) {
    setLetter( location.getRow(), location.getColumn(), letter);
  }

  public void setLetter( int row, int column, char letter) {
    letters[row][column] = letter;
  }

  public void fillWithRandomLetters() {
    for (int i = 0; i < letters.length; i++) {
      for (int j = 0; j < letters[i].length; j++) {
        if (letters[i][j] == ' ') {
          letters[i][j] = getRandomLetter();
        }

      }

    }

  }

  private void initializeLetters() {
    letters = new char[height][width];

    for (int i = 0; i < letters.length; i++) {
      for (int j = 0; j < letters[i].length; j++) {
        letters[i][j] = ' ';
      }

    }

  }

  private char getRandomLetter() {
    return LETTERS.charAt( rand.nextInt( LETTERS.length()));
  }
}
