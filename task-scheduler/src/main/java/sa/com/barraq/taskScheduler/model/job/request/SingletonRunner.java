package sa.com.barraq.taskScheduler.model.job.request;

import lombok.*;
import org.jcsp.lang.One2OneChannel;

import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingletonRunner {
    private One2OneChannel<Object> in;
    private One2OneChannel<Object> rescheduleLimiter;
    private int rescheduleLimiterSize;

    private final ReentrantLock lock = new ReentrantLock();
}
