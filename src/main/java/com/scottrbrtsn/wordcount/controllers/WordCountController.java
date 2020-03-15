package com.scottrbrtsn.wordcount.controllers;

import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.managers.IPhraseManager;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/phrases")
@CrossOrigin(origins = "https://scottrbrtsn.github.io")
public class WordCountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordCountController.class);

    @Autowired
    private IPhraseManager phraseManager;

    @Autowired
    private IPhraseRepository phraseRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<Phrase>> getPhrases() {
        LOGGER.debug("GetPhrases");
        return new ResponseEntity<>(phraseRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/count")
    public ResponseEntity<Integer> countWords(@RequestBody Phrase phrase) {
        LOGGER.debug("CountPhrases");
        return new ResponseEntity<>(phraseManager.countMyWords(phrase, false), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/count/akka")
    public ResponseEntity<Integer> countWordsWithAnActor(@RequestBody Phrase phrase){
        LOGGER.debug("CountPhrases");
        return new ResponseEntity<>(phraseManager.countMyWords(phrase, true), new HttpHeaders(), HttpStatus.OK);
    }
}