package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.*;
import sa.com.barraq.taskScheduler.exceptions.ReceiverTimedOutException;
import sa.com.barraq.taskScheduler.model.job.request.dto.ReceiverResponse;

public class ReceiverWithTimeout implements CSProcess {

    private final AltingChannelInput<Object>[] in;
    private final ReceiverResponse out;
    private final long timeout;
    private final Object lock;

    public ReceiverWithTimeout(final AltingChannelInput<Object>[] in, final ReceiverResponse out, final long timeout) {
        this.in = in;
        this.out = out;
        this.timeout = timeout;
        this.lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock) {
            final CSTimer tim = new CSTimer();
            tim.setAlarm(tim.read () + this.timeout);
            Guard[] guards = new Guard[in.length + 1];
            System.arraycopy(in, 0, guards, 0, in.length);
            guards[in.length] = tim;
            Alternative alt = new Alternative(guards);
            int receivedIndex = alt.select();
            if (receivedIndex == in.length) throw new ReceiverTimedOutException();
            out.setReceivedChIndex(receivedIndex);
            out.setData(in[receivedIndex].read());
        }
    }
}
