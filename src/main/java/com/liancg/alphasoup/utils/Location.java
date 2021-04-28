package com.liancg.alphasoup.utils;

public class Location {
  private final int row;
  private final int column;

  protected Location( int row, int column) {
    this.row = row;
    this.column = column;
  }

  public static Location of( int row, int column) {
    return new Location( row, column);
  }

  @Override
  public String toString() {
    return "[" + row + "," + column + "]";
  }

  public boolean equals( Location other) {
    return row == other.getRow() && column == other.getColumn();
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public Location nextInDirection( WordAngle angle, int step) {
    int endRow = getRow();
    int endColumn = getColumn();

    switch (angle) {
      case EAST:
        endColumn += step;
        break;

      case EAST_SOUTH:
        endRow += step;
        endColumn += step;
        break;

      case SOUTH:
        endRow += step;
        break;

      case WEST_SOUTH:
        endRow += step;
        endColumn -= step;
        break;

      case WEST:
        endColumn -= step;
        break;

      case WEST_NORTH:
        endRow -= step;
        endColumn -= step;
        break;

      case NORTH:
        endRow -= step;
        break;

      case EAST_NORTH:
        endRow -= step;
        endColumn += step;
        break;
    }

    return Location.of( endRow, endColumn);
  }

}
