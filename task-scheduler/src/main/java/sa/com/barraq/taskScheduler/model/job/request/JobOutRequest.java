package sa.com.barraq.taskScheduler.model.job.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sa.com.barraq.taskScheduler.model.job.InternalJob;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

@Getter
@Builder
@AllArgsConstructor
public class JobOutRequest {
    private UUID id;
    private final BlockingQueue<InternalJob> outChan;
}
