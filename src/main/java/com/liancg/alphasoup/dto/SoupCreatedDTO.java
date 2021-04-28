package com.liancg.alphasoup.dto;

import java.util.UUID;

public class SoupCreatedDTO {
  private final UUID id;

  public SoupCreatedDTO( UUID id) {
    this.id = id;
  }

  public String getId() {
    return id.toString();
  }
}
