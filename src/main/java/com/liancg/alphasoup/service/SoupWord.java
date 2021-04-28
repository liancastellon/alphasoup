package com.liancg.alphasoup.service;

import com.liancg.alphasoup.utils.Location;
import com.liancg.alphasoup.utils.WordAngle;

public class SoupWord {
  private final String text;
  private final Location location;
  private final Location endLocation;
  private final WordAngle angle;

  public SoupWord( String text, Location location, WordAngle angle) {
    if (text == null) {
      throw new IllegalArgumentException( "Texto es nulo");
    }

    if (text.trim().length() == 0) {
      throw new IllegalArgumentException( "Texto es vacio o una cadena en blanco");
    }

    if (location.getRow() < 0) {
      throw new IllegalArgumentException( "Fila es negativa");
    }

    if (location.getColumn() < 0) {
      throw new IllegalArgumentException( "Columna es negativa");
    }

    this.text = text;
    this.location = location;
    endLocation = location.nextInDirection( angle, text.length() - 1);
    this.angle = angle;
  }

  @Override
  public String toString() {
    return text + " :" + location + "-" + endLocation;
  }

  public String getText() {
    return text;
  }

  public Location getLocation() {
    return location;
  }

  public WordAngle getAngle() {
    return angle;
  }

  public SoupWord moveTo( Location newLocation) {
    return new SoupWord( text, newLocation, angle);
  }

  public SoupWord changeAngle( WordAngle newAngle) {
    return new SoupWord( text, location, newAngle);
  }

  public int getLength() {
    return text.length();
  }

  public Location getEndLocation() {
    return endLocation;
  }

  public Location[] getLetterLocations() {
    final Location[] result = new Location[text.length()];

    for (int i = 0; i < result.length; i++) {
      result[i] = location.nextInDirection( angle, i);
    }

    return result;
  }
}
