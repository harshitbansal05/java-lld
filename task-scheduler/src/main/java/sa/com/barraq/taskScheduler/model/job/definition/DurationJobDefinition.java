package sa.com.barraq.taskScheduler.model.job.definition;

import lombok.AllArgsConstructor;
import sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors;
import sa.com.barraq.taskScheduler.model.job.schedule.DurationJob;
import sa.com.barraq.taskScheduler.model.job.InternalJob;

import java.time.temporal.TemporalUnit;

@AllArgsConstructor
public class DurationJobDefinition implements JobDefinition {

    private long runEvery;
    private TemporalUnit unit;

    @Override
    public void setup(InternalJob job) throws Exception {
        if (this.runEvery == 0) throw TaskSchedulerErrors.ErrDurationJobIntervalZero;
        job.setJobSchedule(new DurationJob(runEvery, unit));
    }
}
