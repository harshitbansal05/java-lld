package sa.com.barraq.taskScheduler.model.job;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IJob {
    UUID getId();
    String getName();
    List<String> getTags();
    LocalDateTime lastRunAt() throws Exception;
    LocalDateTime nextRunAt() throws Exception;
    List<LocalDateTime> nextRunsAt(int count) throws Exception;
    void runNow() throws Exception;
}
