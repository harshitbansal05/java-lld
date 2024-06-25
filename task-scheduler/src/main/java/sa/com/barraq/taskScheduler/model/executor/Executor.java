package sa.com.barraq.taskScheduler.model.executor;

import lombok.Getter;
import lombok.Setter;
import sa.com.barraq.taskScheduler.model.elector.Elector;
import sa.com.barraq.taskScheduler.model.limit.LimitModeConfig;
import sa.com.barraq.taskScheduler.model.locker.Locker;

import java.time.temporal.TemporalUnit;

@Getter
@Setter
public class Executor {
    Elector elector;
    Locker locker;
    LimitModeConfig limitModeConfig;
    long stopTimeout;
    TemporalUnit stopTimeoutUnit;
}
