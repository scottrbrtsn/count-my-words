package com.scottrbrtsn.wordcount.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedActor {

    @Autowired
    private GreetingService greetingService;

    // constructor

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            ActorRef ref = getSender();
            getSender().tell(greetingService.greet(name), getSelf());
        } else {
            unhandled(message);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Greet {

        private String name;

        // standard constructors/getters

    }
}