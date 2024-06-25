package sa.com.barraq.taskScheduler.model.job.definition;

import lombok.AllArgsConstructor;
import sa.com.barraq.taskScheduler.model.job.InternalJob;

@AllArgsConstructor
public class CronJobDefinition implements JobDefinition {

    private String expression;

    @Override
    public void setup(InternalJob job) throws Exception {

    }
}
