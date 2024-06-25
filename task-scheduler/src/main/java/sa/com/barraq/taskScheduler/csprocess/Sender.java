package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

public class Sender implements CSProcess {
    private final ChannelOutput<Object> out;
    private final Object data;

    public Sender(ChannelOutput<Object> out, Object data) {
        this.out = out;
        this.data = data;
    }

    @Override
    public void run() {
        out.write(data);
    }
}
