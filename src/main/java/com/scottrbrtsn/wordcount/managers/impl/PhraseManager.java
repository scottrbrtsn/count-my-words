package com.scottrbrtsn.wordcount.managers.impl;

import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.domain.Total;
import com.scottrbrtsn.wordcount.managers.IPhraseManager;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import com.scottrbrtsn.wordcount.ras.ITotalsRepository;
import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseManager implements IPhraseManager {

    @Autowired
    private IPhraseService phraseService;

    @Autowired
    private IPhraseRepository phraseRepository;

    @Autowired
    private ITotalsRepository totalRepository;

    public int countMyWords(Phrase phrase){
       int count;

       if(phraseRepository.findById(phrase.getId()) == null){//new phrase
           Total previousCount = totalRepository.findById("WordCount");
           int numNewWords = phraseService.countMyWords(phrase.getPhrase());
           if(previousCount != null) {
               count = previousCount.getTotal() + numNewWords;
           }else{//new phrase && new total
               count = numNewWords;
               previousCount = new Total();
               previousCount.setId("WordCount");
           }

           previousCount.setTotal(count);
           totalRepository.save(previousCount);
           phraseRepository.save(phrase);
       }else{
           count = totalRepository.findById("WordCount").getTotal();
       }

       return count;
    }
}
