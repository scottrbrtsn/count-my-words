package com.scottrbrtsn.wordcount.controllers;

import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/phrases")
public class WordCountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordCountController.class);

    @Autowired
    private IPhraseService phraseService;

    @Autowired
    private IPhraseRepository logsRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<Phrase>> getPhrases() {
        LOGGER.debug("GetPhrases");
        return new ResponseEntity<>(logsRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<List<Phrase>> countWords() {
        LOGGER.debug("CountPhrases");
        return new ResponseEntity<>(logsRepository.findAll(), new HttpHeaders(), HttpStatus.OK);
    }
}