package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

public class Sender implements CSProcess {
    private final ChannelOutput<Object> out;
    private final Object data;
    private final Object lock;

    public Sender(ChannelOutput<Object> out, Object data) {
        this.out = out;
        this.data = data;
        this.lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock) {
            out.write(data);
        }
    }
}
