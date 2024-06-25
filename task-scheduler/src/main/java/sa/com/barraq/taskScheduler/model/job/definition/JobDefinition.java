package sa.com.barraq.taskScheduler.model.job.definition;

import sa.com.barraq.taskScheduler.model.job.InternalJob;

public interface JobDefinition {
    void setup(InternalJob job) throws Exception;
}
