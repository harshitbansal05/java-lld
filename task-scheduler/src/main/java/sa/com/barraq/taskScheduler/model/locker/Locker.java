package sa.com.barraq.taskScheduler.model.locker;

import sa.com.barraq.taskScheduler.model.lock.Lock;

public interface Locker {
    Lock lock(String key) throws Exception;
}
