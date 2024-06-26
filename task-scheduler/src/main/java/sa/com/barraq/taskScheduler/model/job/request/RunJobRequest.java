package sa.com.barraq.taskScheduler.model.job.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jcsp.lang.One2OneChannel;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RunJobRequest {
    private UUID id;
    private final One2OneChannel<Object> outChan;
}
