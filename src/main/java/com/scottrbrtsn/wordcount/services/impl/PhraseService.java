package com.scottrbrtsn.wordcount.services.impl;

import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PhraseService implements IPhraseService {

    @Override
    public int countMyWords(String phrase){
        if(StringUtils.isNullOrEmpty(phrase) || phrase.trim().isEmpty()){//apache commons StringUtils.isEmpty would simplify this.
            return 0;
        }
        //of course the easy way is to just split at spaces
        //but what about punctuation, misspelled words, gibberish, etc...? TODO: verify the leftover items in the array are in fact words
        //the following removes all non-alphanumeric characters and then splits them into an array
        return phrase.replaceAll("[^a-zA-Z0-9 ]", "")
                .trim()
                .split(" ")
                .length;
    }

}