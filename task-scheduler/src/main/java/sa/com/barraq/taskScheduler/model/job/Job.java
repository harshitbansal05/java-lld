package sa.com.barraq.taskScheduler.model.job;

import lombok.AllArgsConstructor;
import org.jcsp.lang.One2OneChannelSymmetric;
import sa.com.barraq.taskScheduler.csprocess.SenderFactory;
import sa.com.barraq.taskScheduler.exceptions.SenderTimedOutException;
import sa.com.barraq.taskScheduler.model.job.request.JobOutRequest;
import sa.com.barraq.taskScheduler.model.job.request.RunJobRequest;
import sa.com.barraq.taskScheduler.utils.TaskSchedulerUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors.ErrJobNotFound;
import static sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors.ErrJobRunNowFailed;
import static sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors.ErrNone;

@AllArgsConstructor
public class Job implements IJob {

    private final UUID id;
    private final String name;
    private final List<String> tags;
    private final SynchronousQueue<JobOutRequest> jobOutRequestQueue;
    private final SynchronousQueue<RunJobRequest> runJobRequestQueue;
    private final One2OneChannelSymmetric<Object> runJobCh, jobOutCh;

    Job(UUID id, String name, List<String> tags, One2OneChannelSymmetric<Object> runJobCh, One2OneChannelSymmetric<Object> jobOutCh) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.jobOutRequestQueue = new SynchronousQueue<>();
        this.runJobRequestQueue = new SynchronousQueue<>();
        this.runJobCh = runJobCh;
        this.jobOutCh = jobOutCh;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<String> getTags() {
        return this.tags;
    }

    @Override
    public LocalDateTime lastRunAt() throws Exception {
        InternalJob job = TaskSchedulerUtils.requestJob(this.id, jobOutRequestQueue);
        if (job == null || job.getId() == null) throw ErrJobNotFound;
        return job.getLastRun();
    }

    @Override
    public LocalDateTime nextRunAt() throws Exception {
        InternalJob job = TaskSchedulerUtils.requestJob(this.id, jobOutRequestQueue);
        if (job == null || job.getId() == null) throw ErrJobNotFound;
        if (job.getNextScheduled().isEmpty()) return null;
        return job.getNextScheduled().getFirst();
    }

    @Override
    public List<LocalDateTime> nextRunsAt(int count) throws Exception {
        InternalJob job = TaskSchedulerUtils.requestJob(this.id, jobOutRequestQueue);
        if (job == null || job.getId() == null) throw ErrJobNotFound;
        if (job.getNextScheduled().isEmpty()) return null;
        int lengthNextScheduled = job.getNextScheduled().size();
        if (count <= lengthNextScheduled) return job.getNextScheduled().subList(0, count);
        List<LocalDateTime> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            if (i < lengthNextScheduled) result.set(i, job.getNextScheduled().get(i));
            else result.set(i, job.getJobSchedule().next(result.getLast()));
        }
        return result;
    }

    @Override
    public void runNow() throws Exception {
        BlockingQueue<Throwable> resp =  new ArrayBlockingQueue<>(1);
        try {
            SenderFactory.getSenderWithTimeout(String.valueOf(runJobCh.hashCode()), runJobCh.out(), new Object(), 100).run();
        } catch (SenderTimedOutException exception) {
            throw ErrJobRunNowFailed;
        }
        Throwable error = resp.poll(100, TimeUnit.MILLISECONDS);
        if (error == null) throw ErrJobRunNowFailed;
        if (error != ErrNone) throw (Exception) error;
    }

    public void runNowDeprecated() throws Exception {
        BlockingQueue<Throwable> resp =  new ArrayBlockingQueue<>(1);
        if (!runJobRequestQueue.offer(new RunJobRequest(id, resp), 100, TimeUnit.MILLISECONDS)) {
            throw ErrJobRunNowFailed;
        }
        Throwable error = resp.poll(100, TimeUnit.MILLISECONDS);
        if (error == null) throw ErrJobRunNowFailed;
        if (error != ErrNone) throw (Exception) error;
    }
}
