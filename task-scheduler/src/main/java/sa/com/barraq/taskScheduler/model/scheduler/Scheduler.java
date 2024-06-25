package sa.com.barraq.taskScheduler.model.scheduler;

import lombok.Setter;
import sa.com.barraq.taskScheduler.enums.LimitMode;
import sa.com.barraq.taskScheduler.model.elector.Elector;
import sa.com.barraq.taskScheduler.model.executor.Executor;
import sa.com.barraq.taskScheduler.model.job.InternalJob;
import sa.com.barraq.taskScheduler.model.job.Job;
import sa.com.barraq.taskScheduler.model.job.definition.JobDefinition;
import sa.com.barraq.taskScheduler.model.job.option.JobOption;
import sa.com.barraq.taskScheduler.model.limit.LimitModeConfig;
import sa.com.barraq.taskScheduler.model.locker.Locker;
import sa.com.barraq.taskScheduler.model.scheduler.option.SchedulerOption;

import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors.*;

@Setter
public class Scheduler implements IScheduler {

    private Executor executor;
    private List<JobOption> globalJobOptions;
    private ANy2

    public static Scheduler newScheduler(SchedulerOption... options) {

    }

    @Override
    public List<Job> jobs() {
        return List.of();
    }

    @Override
    public Job newJob(JobDefinition definition, InternalJob.TaskFunction taskFunction, JobOption... options) throws Exception {
        return null;
    }

    @Override
    public void removeByTags(String... tags) {

    }

    @Override
    public void removeJob(UUID id) throws Exception {

    }

    @Override
    public void shutdown() throws Exception {

    }

    @Override
    public void start() {

    }

    @Override
    public void stopJobs() throws Exception {

    }

    @Override
    public Job update(UUID id, JobDefinition definition, InternalJob.TaskFunction taskFunction, JobOption... options) throws Exception {
        return addOrUpdateJob(id, definition, taskFunction, options);
    }

    @Override
    public int jobsWaitingInQueue() {
        if (executor.getLimitModeConfig() != null && executor.getLimitModeConfig().getMode().equals(LimitMode.LIMIT_MODE_WAIT)) {
            return executor.getLimitModeConfig().getIn().size();
        }
        return 0;
    }

    public static SchedulerOption withDistributedElector(Elector elector) {
        return (scheduler -> {
            if (elector == null) throw ErrWithDistributedElectorNil;
            scheduler.executor.setElector(elector);
        });
    }

    public static SchedulerOption withDistributedLocker(Locker locker) {
        return (scheduler -> {
            if (locker == null) throw ErrWithDistributedLockerNil;
            scheduler.executor.setLocker(locker);
        });
    }

    public static SchedulerOption withGlobalJobOptions(JobOption... jobOptions) {
        return (scheduler -> scheduler.globalJobOptions = List.of(jobOptions));
    }

    public static SchedulerOption withLimitConcurrentJobs(int limit, LimitMode mode) {
        return (scheduler -> {
            if (limit == 0) throw ErrWithLimitConcurrentJobsZero;
            scheduler.executor.setLimitModeConfig(LimitModeConfig.builder()
                    .mode(mode)
                    .limit(limit)
                    .in(new ArrayBlockingQueue<>(1000))
                    .singletonJobs(new HashMap<>())
                    .build());
            if (mode == LimitMode.LIMIT_MODE_RESCHEDULE) {
                scheduler.executor.getLimitModeConfig().setRescheduleLimiter(new ArrayBlockingQueue<>(limit));
            }
        });
    }

    public static SchedulerOption withStopTimeout(long duration, TemporalUnit unit) {
        return (scheduler -> {
            if (duration <= 0) throw ErrWithStopTimeoutZeroOrNegative;
            scheduler.executor.setStopTimeout(duration);
            scheduler.executor.setStopTimeoutUnit(unit);
        });
    }

    private Job addOrUpdateJob(UUID id, JobDefinition definition,
                               InternalJob.TaskFunction taskFunction,
                               JobOption... jobOptions) {
        return null;
    }
}
