package sa.com.barraq.taskScheduler.model.job;

@FunctionalInterface
public interface IRunnable {
    void run(Object... params) throws Exception;
}
