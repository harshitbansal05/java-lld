package sa.com.barraq.taskScheduler.model.scheduler.option;

import sa.com.barraq.taskScheduler.model.scheduler.Scheduler;

public interface SchedulerOption {
    void apply(Scheduler scheduler) throws Exception;
}
