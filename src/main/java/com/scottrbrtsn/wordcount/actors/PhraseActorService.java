package com.scottrbrtsn.wordcount.actors;

import com.scottrbrtsn.wordcount.actors.interfaces.IPhraseActorService;
import org.springframework.stereotype.Service;

@Service
public class PhraseActorService implements IPhraseActorService {

    @Override
    public int countMyWords(String phrase){
        return phrase.split(" ").length;
    }

}