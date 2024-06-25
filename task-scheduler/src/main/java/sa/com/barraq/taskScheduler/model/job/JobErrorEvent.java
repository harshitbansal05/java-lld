package sa.com.barraq.taskScheduler.model.job;

import lombok.Getter;

@Getter
public class JobErrorEvent {
    private Throwable throwable;
}
