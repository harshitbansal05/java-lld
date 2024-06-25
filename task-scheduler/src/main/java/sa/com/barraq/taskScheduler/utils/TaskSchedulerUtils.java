package sa.com.barraq.taskScheduler.utils;

import sa.com.barraq.taskScheduler.model.job.InternalJob;
import sa.com.barraq.taskScheduler.model.job.request.JobOutRequest;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TaskSchedulerUtils {

    public static InternalJob requestJob(UUID id, SynchronousQueue<JobOutRequest> jobOutRequestQueue) throws InterruptedException {
        BlockingQueue<InternalJob> responseChan = new ArrayBlockingQueue<>(1);
        if (!jobOutRequestQueue.offer(new JobOutRequest(id, responseChan), 1, TimeUnit.SECONDS)) return null;
        return responseChan.poll(1, TimeUnit.SECONDS);
    }

    public static void executeJobFunction(Object func, Object... params) throws Exception {

    }
}
