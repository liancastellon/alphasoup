package com.liancg.alphasoup.dto;

public class FoundWordDTO {
  private final String message;

  public FoundWordDTO() {
    message = "Palabra encontrada en la posición";
  }

  public String getMessage() {
    return message;
  }
}
