package sa.com.barraq.taskScheduler.model.limit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sa.com.barraq.taskScheduler.enums.LimitMode;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LimitModeConfig {
    private boolean started;
    private LimitMode mode;
    private int limit;
    private BlockingQueue<Object> rescheduleLimiter;
    private Map<UUID, Object> singletonJobs;
    private BlockingQueue<JobIn> in;

    private Object lock = new Object();

    @Getter
    @Builder
    @AllArgsConstructor
    public static class JobIn {
        private UUID id;
        private boolean shouldSendOut;
    }
}
