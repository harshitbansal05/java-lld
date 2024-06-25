package sa.com.barraq.taskScheduler.model.job.schedule;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Random;

public class RandomDurationJob implements JobSchedule {

    private final long durationMin, durationMax;
    private final TemporalUnit unit;
    private final Random random = new Random(System.nanoTime());

    public RandomDurationJob(long durationMin, long durationMax, TemporalUnit unit) {
        this.durationMin = durationMin;
        this.durationMax = durationMax;
        this.unit = unit;
    }

    @Override
    public LocalDateTime next(LocalDateTime lastRunAt) {
        return lastRunAt.plus(durationMin + random.nextLong(durationMax - durationMin), unit);
    }
}
