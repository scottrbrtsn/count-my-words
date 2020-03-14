package com.scottrbrtsn.wordcount.actors;

import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.springframework.stereotype.Service;

@Service
public class PhraseService implements IPhraseService {

    @Override
    public int countMyWords(String phrase){
        return phrase.split(" ").length;
    }

}