package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.*;
import sa.com.barraq.taskScheduler.exceptions.SenderTimedOutException;

public class SenderWithGuards implements CSProcess {
    private final AltingChannelOutput<Object> out;
    private final AltingChannelInput<Object>[] in;
    private final Object data;
    private final Object lock;

    public SenderWithGuards(AltingChannelOutput<Object> out, AltingChannelInput<Object>[] in, Object data) {
        this.out = out;
        this.in = in;
        this.data = data;
        this.lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock) {
            Guard[] guards = new Guard[in.length + 1];
            System.arraycopy(in, 0, guards, 0, in.length);
            guards[in.length] = out;
            Alternative alt = new Alternative(guards);
            int receivedIndex = alt.select();
            if (receivedIndex == in.length) out.write(data);
            else throw new SenderTimedOutException();
        }
    }
}
