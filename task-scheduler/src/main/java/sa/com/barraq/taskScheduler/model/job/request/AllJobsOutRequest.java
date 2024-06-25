package sa.com.barraq.taskScheduler.model.job.request;

import sa.com.barraq.taskScheduler.model.job.Job;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class AllJobsOutRequest {
    SynchronousQueue<List<Job>> outChan;
}
