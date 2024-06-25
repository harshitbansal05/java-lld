package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.*;
import sa.com.barraq.taskScheduler.exceptions.SenderTimedOutException;

public class SenderWithTimeout implements CSProcess {

    private final AltingChannelOutput<Object> out;
    private final Object data;
    private final long timeout;
    private final Object lock;

    public SenderWithTimeout(AltingChannelOutput<Object> out, Object data, long timeout) {
        this.out = out;
        this.data = data;
        this.timeout = timeout;
        this.lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock) {
            final CSTimer tim = new CSTimer();
            tim.setAlarm(tim.read () + this.timeout);
            Alternative alt = new Alternative(new Guard[]{this.out, tim});
            switch (alt.select()) {
                case 0: {
                    out.write(data);
                    break;
                }
                case 1: throw new SenderTimedOutException();
            }
        }
    }
}
