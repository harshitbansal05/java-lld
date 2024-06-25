package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.AltingChannelOutput;
import org.jcsp.lang.CSProcess;

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

        }
    }
}
