package com.liancg.alphasoup.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AlphaSoupRepository {
  private final Map<UUID, AlphaSoup> items = new HashMap<>();

  public UUID create( int width, int height, SoupOptions options) {
    final List<String> wordList = getWordList();
    final SoupGenerator generator = new SoupGenerator( width, height, options, wordList);

    final AlphaSoup result = generator.generate( 5);

    return add( result);
  }

  private UUID add( AlphaSoup soup) {
    final UUID uuid = UUID.randomUUID();

    items.put( uuid, soup);

    return uuid;
  }

  private List<String> getWordList() {
    final Path path = Paths.get( "src/main/java/word-list.txt");

    List<String> result;

    try {
      result = Files.readAllLines( path, StandardCharsets.UTF_8);
    }
    catch (final IOException ex) {
      throw new RuntimeException( ex);
    }

    return result;
  }

  public AlphaSoup find( UUID id) {
    return items.get( id);
  }
}
