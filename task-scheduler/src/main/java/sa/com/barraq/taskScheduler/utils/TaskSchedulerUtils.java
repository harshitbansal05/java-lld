package sa.com.barraq.taskScheduler.utils;

import org.jcsp.lang.*;
import sa.com.barraq.taskScheduler.csprocess.ReceiverFactory;
import sa.com.barraq.taskScheduler.csprocess.SenderFactory;
import sa.com.barraq.taskScheduler.exceptions.ReceiverTimedOutException;
import sa.com.barraq.taskScheduler.exceptions.SenderTimedOutException;
import sa.com.barraq.taskScheduler.model.job.InternalJob;
import sa.com.barraq.taskScheduler.model.job.request.JobOutRequest;
import sa.com.barraq.taskScheduler.model.job.request.dto.ReceiverResponse;

import java.util.UUID;

public class TaskSchedulerUtils {

    public static InternalJob requestJob(UUID id, One2OneChannelSymmetric<Object> jobOutRequestCh) {
        return requestJobWithTimeout(id, jobOutRequestCh, 1000);
    }

    public static InternalJob requestJobWithGuards(AltingChannelInput<Object>[] in,
                                                   UUID id,
                                                   One2OneChannelSymmetric<Object> jobOutRequestCh) {
        One2OneChannelSymmetric<Object> resp = Channel.one2oneSymmetric();
        JobOutRequest request = JobOutRequest.builder().id(id).outChan(resp).build();
        CSProcess sender = SenderFactory.getSenderWithGuards(String.valueOf(jobOutRequestCh.hashCode()), jobOutRequestCh.out(), in, request);
        try {
            sender.run();
        } catch (PoisonException | SenderTimedOutException exception) {
            return null;
        }

        ReceiverResponse response = new ReceiverResponse();
        AltingChannelInput<Object>[] guards = new AltingChannelInput[in.length + 1];
        System.arraycopy(in, 0, guards, 0, in.length);
        guards[in.length] = resp.in();
        try {
            ReceiverFactory.getReceiver(String.valueOf(resp.hashCode()), guards, response).run();
        } catch (PoisonException | ReceiverTimedOutException exception) {
            return null;
        }
        return (InternalJob) response.getData();
    }

    public static InternalJob requestJobWithTimeout(UUID id,
                                                    One2OneChannelSymmetric<Object> jobOutRequestCh,
                                                    long timeout) {
        One2OneChannelSymmetric<Object> resp = Channel.one2oneSymmetric();
        JobOutRequest request = JobOutRequest.builder().id(id).outChan(resp).build();
        CSProcess sender = SenderFactory.getSenderWithTimeout(String.valueOf(jobOutRequestCh.hashCode()), jobOutRequestCh.out(), request, timeout);
        try {
            sender.run();
        } catch (PoisonException | SenderTimedOutException exception) {
            return null;
        }

        ReceiverResponse response = new ReceiverResponse();
        try {
            ReceiverFactory.getReceiverWithTimeout(String.valueOf(resp.hashCode()), new AltingChannelInput[]{resp.in()}, response, timeout).run();
        } catch (PoisonException | ReceiverTimedOutException exception) {
            return null;
        }
        return (InternalJob) response.getData();
    }

    public static void executeJobFunction(Object func, Object... params) throws Exception {

    }
}
