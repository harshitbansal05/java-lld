package sa.com.barraq.taskScheduler.csprocess;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

public class Poisoner implements CSProcess {
    private final ChannelOutput<Object> out;
    private final int poison;

    public Poisoner(ChannelOutput<Object> out, int poison) {
        this.out = out;
        this.poison = poison;
    }

    @Override
    public void run() {
        out.poison(poison);
    }
}
