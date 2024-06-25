package sa.com.barraq.model;

import lombok.Getter;

@Getter
public class FailureEvent extends Event {
    private final Event event;
    private final Throwable throwable;

    public FailureEvent(Event event, Throwable throwable, long failureTimestamp) {
        super("dead-letter-queue", EventType.ERROR, throwable.getMessage(), failureTimestamp);
        this.event = event;
        this.throwable = throwable;
    }
}
