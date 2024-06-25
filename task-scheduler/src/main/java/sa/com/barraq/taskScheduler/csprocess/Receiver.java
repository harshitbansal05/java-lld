package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import sa.com.barraq.taskScheduler.model.job.request.dto.ReceiverResponse;

public class Receiver implements CSProcess {

    private final AltingChannelInput<Object>[] in;
    private final ReceiverResponse out;
    private final Object lock;

    public Receiver(final AltingChannelInput<Object>[] in, final ReceiverResponse out) {
        this.in = in;
        this.out = out;
        this.lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock) {
            Guard[] guards = new Guard[in.length];
            System.arraycopy(in, 0, guards, 0, in.length);
            Alternative alt = new Alternative(guards);
            int receivedIndex = alt.select();
            out.setReceivedChIndex(receivedIndex);
            out.setData(in[receivedIndex].read());
        }
    }
}
