package com.liancg.alphasoup.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liancg.alphasoup.dto.ErrorDTO;
import com.liancg.alphasoup.dto.FoundWordDTO;
import com.liancg.alphasoup.dto.NewSoupDTO;
import com.liancg.alphasoup.dto.SelectWordDTO;
import com.liancg.alphasoup.dto.SoupCreatedDTO;
import com.liancg.alphasoup.dto.SoupWordsDTO;
import com.liancg.alphasoup.service.AlphaSoup;
import com.liancg.alphasoup.service.AlphaSoupRepository;
import com.liancg.alphasoup.service.SoupOptions;
import com.liancg.alphasoup.service.SoupPrinter;
import com.liancg.alphasoup.utils.Location;

@RestController
public class MainController {
  @Autowired
  private AlphaSoupRepository repository;

  @Autowired
  private SoupPrinter printer;

  @GetMapping(path = "/list/{id}")
  public Object listWords( @PathVariable UUID id) {
    final AlphaSoup soup = repository.find( id);

    Object response;

    if (soup == null) {
      throw new RuntimeException( "No se encuentra la sopa de letras con este ID");
    }

    response = new SoupWordsDTO( soup.getWords()).getWords();

    return response;
  }

  @GetMapping(path = "/view/{id}")
  public Object showSoup( @PathVariable UUID id) {
    final AlphaSoup soup = repository.find( id);

    Object response;

    if (soup == null) {
      throw new RuntimeException( "No se encuentra la sopa de letras con este ID");
    }

    response = printSoup( soup);

    return response;
  }

  @PostMapping
  public Object newSoup( @RequestBody NewSoupDTO newSoupDTO) {
    final SoupOptions options = buildOptions( newSoupDTO);

    Object response;

    try {
      final UUID uuid = repository.create( newSoupDTO.w, newSoupDTO.h, options);

      response = new SoupCreatedDTO( uuid);
    }
    catch (final Exception ex) {
      response = new ErrorDTO( ex.getLocalizedMessage());
    }

    return response;
  }

  @PutMapping(path = "/{id}")
  public Object selectWord( @PathVariable UUID id, @RequestBody SelectWordDTO selectWordDto) {
    final AlphaSoup soup = repository.find( id);

    Object response;

    if (soup == null) {
      throw new RuntimeException( "No se encuentra la sopa de letras con este ID");
    }

    if (soup.selectWord( Location.of( selectWordDto.sr, selectWordDto.sc),
      Location.of( selectWordDto.er, selectWordDto.ec))) {
      response = new FoundWordDTO();
    }
    else {
      response = new ErrorDTO( "No hay ninguna palabra en esa ubicación");
    }

    return response;
  }

  private SoupOptions buildOptions( NewSoupDTO newSoupDTO) {
    final SoupOptions result = SoupOptions.defaults();

    if (newSoupDTO.ltr) {
      result.allowLeftToRight();
    }

    if (newSoupDTO.rtl) {
      result.allowRightToLeft();
    }

    if (newSoupDTO.ttb) {
      result.allowTopToDown();
    }

    if (newSoupDTO.btt) {
      result.allowDownToTop();
    }

    if (newSoupDTO.d) {
      result.allowDiagonals();
    }

    return result;
  }

  private String printSoup( AlphaSoup soup) {
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    printer.print( soup, new PrintStream( outStream));

    try {
      return outStream.toString( "UTF8");
    }
    catch (final UnsupportedEncodingException ex) {
      throw new RuntimeException( ex);
    }
  }
}
