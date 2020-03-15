package com.scottrbrtsn.wordcount.managers;

import com.scottrbrtsn.wordcount.domain.Phrase;

public interface IPhraseManager {

    int countMyWords(Phrase phrase, boolean useActor);


}
