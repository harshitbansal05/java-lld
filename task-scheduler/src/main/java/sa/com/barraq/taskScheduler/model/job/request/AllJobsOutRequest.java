package sa.com.barraq.taskScheduler.model.job.request;

import org.jcsp.lang.One2OneChannelSymmetric;
import sa.com.barraq.taskScheduler.model.job.Job;

import java.util.List;

public class AllJobsOutRequest {
    One2OneChannelSymmetric<List<Job>> outChan;
}
