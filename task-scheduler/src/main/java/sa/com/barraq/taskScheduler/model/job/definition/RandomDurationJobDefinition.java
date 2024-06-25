package sa.com.barraq.taskScheduler.model.job.definition;

import lombok.AllArgsConstructor;
import sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors;
import sa.com.barraq.taskScheduler.model.job.schedule.RandomDurationJob;
import sa.com.barraq.taskScheduler.model.job.InternalJob;

import java.time.temporal.TemporalUnit;

@AllArgsConstructor
public class RandomDurationJobDefinition implements JobDefinition {

    private long runEveryMin, runEveryMax;
    private TemporalUnit unit;

    @Override
    public void setup(InternalJob job) throws Exception {
        if (this.runEveryMin >= this.runEveryMax) throw TaskSchedulerErrors.ErrDurationRandomJobMinMax;
        job.setJobSchedule(new RandomDurationJob(runEveryMin, runEveryMax, unit));
    }
}
