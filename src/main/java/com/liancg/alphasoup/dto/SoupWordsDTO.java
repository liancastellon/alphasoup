package com.liancg.alphasoup.dto;

import java.util.ArrayList;
import java.util.List;

import com.liancg.alphasoup.service.SoupWord;

public class SoupWordsDTO {
  private final String[] words;

  public SoupWordsDTO( SoupWord[] words) {
    final List<String> list = new ArrayList<>();

    for (final SoupWord word: words) {
      list.add( word.getText());
    }

    this.words = list.toArray( new String[0]);
  }

  public String[] getWords() {
    return words;
  }
}
