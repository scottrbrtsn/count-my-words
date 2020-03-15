package com.scottrbrtsn.wordcount.actors;

import akka.actor.UntypedActor;
import com.scottrbrtsn.wordcount.actors.interfaces.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Actor
public class PhraseActor extends UntypedActor {

    @Autowired
    private PhraseActorService phraseService;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Phrase) {
            String phrase = ((Phrase) message).getPhrase();
            getSender().tell(phraseService.countMyWords(phrase), getSelf());
        } else {
            unhandled(message);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Phrase {
        private long id;
        private String phrase;

    }
}