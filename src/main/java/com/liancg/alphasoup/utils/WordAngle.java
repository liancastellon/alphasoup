package com.liancg.alphasoup.utils;

/**
 * Ángulo de inclinacion de la palabra/forma en que se lee. Se listan en la
 * direccion de las manecillas del reloj, comenzando con las palabras de
 * izquierda a derecha.
 */
public enum WordAngle {
  // De izquierda a derecha
  EAST,

  // 45 grados a la derecha + abajo
  EAST_SOUTH,

  // Vertical,
  SOUTH,

  // 45 grados a la izquierda + abajo
  WEST_SOUTH,

  // De derecha a izquierda
  WEST,

  // 45 grados a la izquierda + arriba
  WEST_NORTH,

  // De abajo hacia arriba
  NORTH,

  // 45 grados, a la derecha + arriba
  EAST_NORTH;

  private static WordAngle[] allValues = values();

  public WordAngle next() {
    return allValues[(ordinal() + 1) % allValues.length];
  }
}
