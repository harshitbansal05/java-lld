package sa.com.barraq.taskScheduler.model.job;

import com.cronutils.utils.StringUtils;
import lombok.*;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;
import sa.com.barraq.taskScheduler.csprocess.SenderFactory;
import sa.com.barraq.taskScheduler.enums.LimitMode;
import sa.com.barraq.taskScheduler.model.job.option.JobOption;
import sa.com.barraq.taskScheduler.model.job.option.StartAtOption;
import sa.com.barraq.taskScheduler.model.job.schedule.JobSchedule;
import sa.com.barraq.taskScheduler.model.locker.Locker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static sa.com.barraq.taskScheduler.exceptions.TaskSchedulerErrors.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalJob {
    private UUID id;
    private String name;
    private List<String> tags;
    private JobSchedule jobSchedule;

    private List<LocalDateTime> nextScheduled;

    private LocalDateTime lastRun;
    private Object function;
    private List<Object> parameters;
    private boolean singletonMode;
    private LimitMode singletonLimitMode;
    private int limitRunsTo;
    private LocalDateTime startTime;
    private boolean startImmediately;

    private IRunnable afterJobRuns;
    private IRunnable beforeJobRuns;
    private IRunnable afterJobRunsWithError;

    private Locker locker;
    private One2OneChannel<Object> cancelCh = Channel.one2one(1);

    public void stop() {
        SenderFactory.getPoisoner(String.valueOf(cancelCh.hashCode()), cancelCh.out(), 1).run();
    }

    public void resetCancelCh() {
        cancelCh = Channel.one2one(1);
    }

    @Getter
    @AllArgsConstructor
    static class Task {
        Object function;
        List<Object> parameters;
    }

    public interface TaskFunction {
        Task getTask();
    }

    interface EventListener {
        void listen(InternalJob job) throws Exception;
    }

    static TaskFunction newTask(Object function, List<Object> parameters) {
        return () -> new Task(function, parameters);
    }

    static JobOption withDistributedJobLocker(Locker locker) {
        return (job) -> {
            if (locker == null) throw ErrWithDistributedJobLockerNil;
            job.locker = locker;
        };
    }

    static JobOption withLimitedRuns(int limit) {
        return (job) -> {
            job.limitRunsTo = limit;
        };
    }

    static JobOption withLimitedRuns(String name) {
        return (job) -> {
            if (StringUtils.isEmpty(name)) throw ErrWithNameEmpty;
            job.name = name;
        };
    }

    static JobOption withSingletonMode(LimitMode mode) {
        return (job) -> {
            job.singletonMode = true;
            job.singletonLimitMode = mode;
        };
    }

    static JobOption withTags(String... tags) {
        return (job) -> {
            job.tags = List.of(tags);
        };
    }

    static JobOption withStartAt(StartAtOption option) {
        return option::apply;
    }

    static StartAtOption withStartImmediately() {
        return (job) -> job.startImmediately = true;
    }

    static StartAtOption withStartDateTime(LocalDateTime time) {
        return (job) -> {
            if (time.isBefore(LocalDateTime.now())) throw ErrWithStartDateTimePast;
            job.startTime = time;
        };
    }

    static JobOption withEventListeners(EventListener... listeners) {
        return (job) -> {
            for (EventListener listener : listeners) {
                listener.listen(job);
            }
        };
    }

    static EventListener beforeJobRuns(IRunnable eventListenerFunc) {
        return (job) -> {
            if (eventListenerFunc == null) throw ErrEventListenerFuncNil;
            job.beforeJobRuns = eventListenerFunc;
        };
    }

    static EventListener afterJobRuns(IRunnable eventListenerFunc) {
        return (job) -> {
            if (eventListenerFunc == null) throw ErrEventListenerFuncNil;
            job.afterJobRuns = eventListenerFunc;
        };
    }

    static EventListener afterJobRunsWithError(IRunnable eventListenerFunc) {
        return (job) -> {
            if (eventListenerFunc == null) throw ErrEventListenerFuncNil;
            job.afterJobRunsWithError = eventListenerFunc;
        };
    }
}
