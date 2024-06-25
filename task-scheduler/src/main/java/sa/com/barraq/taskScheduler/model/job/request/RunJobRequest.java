package sa.com.barraq.taskScheduler.model.job.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

@Getter
@Builder
@AllArgsConstructor
public class RunJobRequest {
    private UUID id;
    private final BlockingQueue<Throwable> outChan;
}
