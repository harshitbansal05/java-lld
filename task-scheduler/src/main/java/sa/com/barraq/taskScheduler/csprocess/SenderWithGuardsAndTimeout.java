package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.*;
import sa.com.barraq.taskScheduler.exceptions.SenderTimedOutException;

public class SenderWithGuardsAndTimeout implements CSProcess {
    private final AltingChannelOutput<Object> out;
    private final AltingChannelInput<Object>[] in;
    private final Object data;
    private final long timeout;
    private final Object lock;

    public SenderWithGuardsAndTimeout(AltingChannelOutput<Object> out, AltingChannelInput<Object>[] in, Object data, long timeout) {
        this.out = out;
        this.in = in;
        this.data = data;
        this.timeout = timeout;
        this.lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock) {
            final CSTimer tim = new CSTimer();
            tim.setAlarm(tim.read () + this.timeout);
            Guard[] guards = new Guard[in.length + 2];
            System.arraycopy(in, 0, guards, 0, in.length);
            guards[in.length] = tim;
            guards[in.length + 1] = out;
            Alternative alt = new Alternative(guards);
            int receivedIndex = alt.select();
            if (receivedIndex == in.length + 1) out.write(data);
            else throw new SenderTimedOutException();
        }
    }
}
