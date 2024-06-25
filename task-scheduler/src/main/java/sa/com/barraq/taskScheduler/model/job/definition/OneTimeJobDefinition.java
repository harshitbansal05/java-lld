package sa.com.barraq.taskScheduler.model.job.definition;

import lombok.AllArgsConstructor;
import sa.com.barraq.taskScheduler.model.job.InternalJob;

import java.time.LocalDateTime;

import static sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors.ErrOneTimeJobStartDateTimePast;

@AllArgsConstructor
public class OneTimeJobDefinition implements JobDefinition {

    private boolean startImmediately;
    private LocalDateTime runAt;

    @Override
    public void setup(InternalJob job) throws Exception {
        if (this.startImmediately) {
            job.setStartImmediately(true);
            return;
        }
        if (runAt.isBefore(LocalDateTime.now())) throw ErrOneTimeJobStartDateTimePast;
        job.setStartTime(runAt);
    }
}
