package sa.com.barraq.taskScheduler.model.job.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;
import sa.com.barraq.taskScheduler.model.job.InternalJob;

@Getter
@Builder
@AllArgsConstructor
public class NewJobIn {
    private InternalJob job;
    private final One2OneChannel<Object> cancelCh = Channel.one2one(1);
}
