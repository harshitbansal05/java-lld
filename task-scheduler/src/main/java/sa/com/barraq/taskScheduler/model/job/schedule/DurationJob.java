package sa.com.barraq.taskScheduler.model.job.schedule;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

@AllArgsConstructor
public class DurationJob implements JobSchedule {

    private long duration;
    private TemporalUnit unit;

    @Override
    public LocalDateTime next(LocalDateTime lastRunAt) {
        return lastRunAt.plus(duration, unit);
    }
}
