package sa.com.barraq.callback;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    PriorityQueue<CallBack> q = new PriorityQueue<>((o1, o2) -> (int) (o1.executeAt - o2.executeAt));

    public void start() throws InterruptedException {
        while (true) {
            lock.lock();
            while (q.isEmpty()) {
                condition.await();
            }
            while (!q.isEmpty()) {
                CallBack callBack = q.peek();
                if (!condition.await(callBack.executeAt - System.currentTimeMillis(), TimeUnit.MILLISECONDS)) {
                    q.poll();
                    lock.unlock();
                    System.out.println(callBack.message);
                    break;
                }
            }
        }
    }

    public void registerCallback(CallBack callBack) {
        lock.lock();
        q.add(callBack);
        condition.signal();
        lock.unlock();
    }

    static class CallBack {
        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.message = message;
            this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
        }
    }
}
