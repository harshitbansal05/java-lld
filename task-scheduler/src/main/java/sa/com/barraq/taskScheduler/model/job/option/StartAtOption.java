package sa.com.barraq.taskScheduler.model.job.option;

import sa.com.barraq.taskScheduler.model.job.InternalJob;

public interface StartAtOption {
    void apply(InternalJob job) throws Exception;
}
