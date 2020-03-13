package com.scottrbrtsn.wordcount.managers.impl;

import com.scottrbrtsn.wordcount.managers.IPhraseManager;
import org.springframework.stereotype.Service;

@Service
public class PhraseManager implements IPhraseManager {

    public int countMyWords(String phrase){
        return phrase.split(" ").length;
    }
}
