package com.liancg.alphasoup.service;

import com.liancg.alphasoup.utils.WordAngle;

public class SoupOptions {
  private boolean leftToRight = true;
  private boolean rightToLeft = false;
  private boolean topToDown = true;
  private boolean downToTop = false;
  private boolean diagonals = false;

  protected SoupOptions() {
  }

  public static SoupOptions defaults() {
    return new SoupOptions();
  }

  public static SoupOptions allOptions() {
    return new SoupOptions().allowReverse().allowDiagonals();
  }

  public SoupOptions allowLeftToRight() {
    leftToRight = true;

    return this;
  }

  public SoupOptions allowRightToLeft() {
    rightToLeft = true;

    return this;
  }

  public SoupOptions allowTopToDown() {
    topToDown = true;

    return this;
  }

  public SoupOptions allowDownToTop() {
    downToTop = true;

    return this;
  }

  public SoupOptions allowDiagonals() {
    diagonals = true;

    return this;
  }

  public SoupOptions allowReverse() {
    return allowRightToLeft().allowDownToTop();
  }

  public boolean allowsAngle( WordAngle angle) {
    switch (angle) {
      case EAST:
        return leftToRight;

      case WEST:
        return rightToLeft;

      case SOUTH:
        return topToDown;

      case NORTH:
        return downToTop;

      case EAST_NORTH:
      case EAST_SOUTH:
      case WEST_NORTH:
      case WEST_SOUTH:
        return diagonals;

      default:
        return false;
    }

  }
}
