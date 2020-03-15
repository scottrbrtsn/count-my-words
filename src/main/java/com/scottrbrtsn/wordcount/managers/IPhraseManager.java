package com.scottrbrtsn.wordcount.managers;

import com.scottrbrtsn.wordcount.domain.Phrase;

public interface IPhraseManager {

    int countMyWords(Phrase phrase);

    int countMyWordsWithAnActor(Phrase phrase) throws Exception;

}
