package com.scottrbrtsn.wordcount.actors;

import akka.actor.UntypedActor;
import com.scottrbrtsn.wordcount.actors.interfaces.Actor;
import com.scottrbrtsn.wordcount.actors.interfaces.IPhraseActorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Actor
public class PhraseActor extends UntypedActor {

    @Autowired
    private IPhraseActorService phraseService;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Phrase) {
            String phrase = ((Phrase) message).getPhrase();
            getSender().tell(phraseService.countMyWords(phrase), getSelf());
        } else {
            unhandled(message);
        }
    }

    @Entity
    @Table(name = "phrases")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Phrase {

        @Id
        @Column
        private String id;

        @Column
        private String phrase;

    }
}