package sa.com.barraq.taskScheduler.model.elector;

public interface Elector {
    boolean isLeader() throws Exception;
}
