package sa.com.barraq.taskScheduler.model.scheduler;

import sa.com.barraq.taskScheduler.model.job.InternalJob;
import sa.com.barraq.taskScheduler.model.job.Job;
import sa.com.barraq.taskScheduler.model.job.definition.JobDefinition;
import sa.com.barraq.taskScheduler.model.job.option.JobOption;

import java.util.List;
import java.util.UUID;

public interface IScheduler {
    List<Job> jobs();
    Job newJob(JobDefinition definition, InternalJob.TaskFunction taskFunction, JobOption... options) throws Exception;
    void removeByTags(String... tags);
    void removeJob(UUID id) throws Exception;
    void shutdown() throws Exception;
    void start();
    void stopJobs() throws Exception;
    Job update(UUID id, JobDefinition definition, InternalJob.TaskFunction taskFunction, JobOption... options) throws Exception;
    int jobsWaitingInQueue();
}
