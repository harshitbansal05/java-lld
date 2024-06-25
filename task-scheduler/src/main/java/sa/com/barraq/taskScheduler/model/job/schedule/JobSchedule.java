package sa.com.barraq.taskScheduler.model.job.schedule;

import java.time.LocalDateTime;

public interface JobSchedule {
    LocalDateTime next(LocalDateTime lastRunAt);
}
