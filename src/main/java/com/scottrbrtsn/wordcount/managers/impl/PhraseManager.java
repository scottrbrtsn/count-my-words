package com.scottrbrtsn.wordcount.managers.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.scottrbrtsn.wordcount.actors.PhraseActor;
import com.scottrbrtsn.wordcount.actors.SpringExtension;
import com.scottrbrtsn.wordcount.domain.Phrase;
import com.scottrbrtsn.wordcount.domain.Total;
import com.scottrbrtsn.wordcount.managers.IPhraseManager;
import com.scottrbrtsn.wordcount.ras.IPhraseRepository;
import com.scottrbrtsn.wordcount.ras.ITotalsRepository;
import com.scottrbrtsn.wordcount.services.IPhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@Service
public class PhraseManager implements IPhraseManager {

    @Autowired
    private IPhraseService phraseService;

    @Autowired
    private IPhraseRepository phraseRepository;

    @Autowired
    private ITotalsRepository totalRepository;

    @Autowired
    private ActorSystem system;

    @Autowired
    private ApplicationContext context;

    @Override
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

    @Override
    public int countMyWordsWithAnActor(Phrase phrase) throws Exception{
        int count;

        if(phraseRepository.findById(phrase.getId()) == null){//new phrase
            Total previousCount = totalRepository.findById("WordCount");
            int numNewWords = actorCount(phrase);
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

    private int actorCount(Phrase phrase) throws Exception{
        ActorRef wordCounter = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                .props("phraseActor"), "greeter");

        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Future<Object> result = ask(wordCounter, new PhraseActor.Phrase(phrase.getId(), phrase.getPhrase()), timeout);
        return (Integer) Await.result(result, duration);
    }
}
