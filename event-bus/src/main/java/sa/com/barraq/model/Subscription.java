package sa.com.barraq.model;

import lombok.Getter;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
public class Subscription {
    private final String topic;
    private final String subscriber;
    private final Predicate<Event> precondition;
    private final Function<Event, CompletionStage<Void>> eventHandler;
    private final int numberOfRetries;
    private final LongAdder currentIndex;

    public Subscription(final String topic,
                        final String subscriber,
                        final Predicate<Event> precondition,
                        final Function<Event, CompletionStage<Void>> eventHandler,
                        final int numberOfRetries) {
        this.topic = topic;
        this.subscriber = subscriber;
        this.precondition = precondition;
        this.eventHandler = eventHandler;
        this.currentIndex = new LongAdder();
        this.numberOfRetries = numberOfRetries;
    }

    public void setCurrentIndex(final int offset) {
        currentIndex.reset();
        currentIndex.add(offset);
    }
}
