package sa.com.barraq.taskScheduler.model.job.schedule;

import java.time.LocalDateTime;

public class OneTimeJob implements JobSchedule {

    @Override
    public LocalDateTime next(LocalDateTime lastRunAt) {
        return LocalDateTime.now();
    }
}
