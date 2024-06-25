package sa.com.barraq.taskScheduler.model.job.definition;

import java.time.temporal.TemporalUnit;

public class JobDefinitionFactory {

    public static JobDefinition createDurationJob(long runEvery, TemporalUnit unit) {
        return new DurationJobDefinition(runEvery, unit);
    }

    public static JobDefinition createRandomDurationJob(long runEveryMin, long runEveryMax, TemporalUnit unit) {
        return new RandomDurationJobDefinition(runEveryMin, runEveryMax, unit);
    }
}
