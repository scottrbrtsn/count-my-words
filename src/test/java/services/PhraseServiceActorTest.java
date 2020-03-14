package services;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.scottrbrtsn.wordcount.actors.AkkaConfiguration;
import com.scottrbrtsn.wordcount.actors.GreetingActor;
import com.scottrbrtsn.wordcount.actors.SpringExtension;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@ContextConfiguration(classes = AkkaConfiguration.class)
public class PhraseServiceActorTest  extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ActorSystem system;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testCountMyWords_firstCountAndNoTotals_returnsNewTotal() throws Exception{
        ActorRef greeter = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                .props("greetingActor"), "greeter");

        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Future<Object> result = ask(greeter, new GreetingActor.Greet("John"), timeout);

        Assert.assertEquals("Hello, John", Await.result(result, duration));

    }

}
