package com.scottrbrtsn.wordcount.services.impl;

import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.springframework.stereotype.Service;

@Service
public class PhraseService implements IPhraseService {

    @Override
    public int countMyWords(String phrase){
        return phrase.split(" ").length;
    }

    public String print(String name){
        return "hello " + name;
    }

}