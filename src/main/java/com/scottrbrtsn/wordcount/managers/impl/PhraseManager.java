package com.scottrbrtsn.wordcount.managers.impl;

import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.managers.IPhraseManager;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseManager implements IPhraseManager {

    @Autowired
    IPhraseService phraseService;

    @Autowired
    IPhraseRepository phraseRepository;

    public int countMyWords(Phrase phrase){
       int count = -1;

       if(phraseRepository.findById(phrase.getId()) == null){
           count = phraseService.countMyWords(phrase.getPhrase());
       }

       return count;
    }
}
