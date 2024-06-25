package sa.com.barraq.taskScheduler.exceptions;

public class TaskSchedulerErrors {
    public static final TaskSchedulerException ErrCronJobParse = new TaskSchedulerException("task_scheduler: CronJob: crontab parse failure");
    public static final TaskSchedulerException ErrDailyJobAtTimeNil = new TaskSchedulerException("task_scheduler: DailyJob: atTime within atTimes must not be nil");
    public static final TaskSchedulerException ErrDailyJobAtTimesNil = new TaskSchedulerException("task_scheduler: DailyJob: atTimes must not be nil");
    public static final TaskSchedulerException ErrDailyJobHours = new TaskSchedulerException("task_scheduler: DailyJob: atTimes hours must be between 0 and 23 inclusive");
    public static final TaskSchedulerException ErrDailyJobMinutesSeconds = new TaskSchedulerException("task_scheduler: DailyJob: atTimes minutes and seconds must be between 0 and 59 inclusive");
    public static final TaskSchedulerException ErrDurationJobIntervalZero = new TaskSchedulerException("task_scheduler: DurationJob: time interval is 0");
    public static final TaskSchedulerException ErrDurationRandomJobMinMax = new TaskSchedulerException("task_scheduler: DurationRandomJob: minimum duration must be less than maximum duration");
    public static final TaskSchedulerException ErrEventListenerFuncNil = new TaskSchedulerException("task_scheduler: eventListenerFunc must not be nil");
    public static final TaskSchedulerException ErrJobNotFound = new TaskSchedulerException("task_scheduler: job not found");
    public static final TaskSchedulerException ErrJobRunNowFailed = new TaskSchedulerException("task_scheduler: Job: RunNow: scheduler unreachable");
    public static final TaskSchedulerException ErrMonthlyJobDays = new TaskSchedulerException("task_scheduler: MonthlyJob: daysOfTheMonth must be between 31 and -31 inclusive, and not 0");
    public static final TaskSchedulerException ErrMonthlyJobAtTimeNil = new TaskSchedulerException("task_scheduler: MonthlyJob: atTime within atTimes must not be nil");
    public static final TaskSchedulerException ErrMonthlyJobAtTimesNil = new TaskSchedulerException("task_scheduler: MonthlyJob: atTimes must not be nil");
    public static final TaskSchedulerException ErrMonthlyJobDaysNil = new TaskSchedulerException("task_scheduler: MonthlyJob: daysOfTheMonth must not be nil");
    public static final TaskSchedulerException ErrMonthlyJobHours = new TaskSchedulerException("task_scheduler: MonthlyJob: atTimes hours must be between 0 and 23 inclusive");
    public static final TaskSchedulerException ErrMonthlyJobMinutesSeconds = new TaskSchedulerException("task_scheduler: MonthlyJob: atTimes minutes and seconds must be between 0 and 59 inclusive");
    public static final TaskSchedulerException ErrNewJobTaskNil = new TaskSchedulerException("task_scheduler: NewJob: Task must not be nil");
    public static final TaskSchedulerException ErrNewJobTaskNotFunc = new TaskSchedulerException("task_scheduler: NewJob: Task.Function must be of kind reflect.Func");
    public static final TaskSchedulerException ErrNewJobWrongNumberOfParameters = new TaskSchedulerException("task_scheduler: NewJob: Number of provided parameters does not match expected");
    public static final TaskSchedulerException ErrNewJobWrongTypeOfParameters = new TaskSchedulerException("task_scheduler: NewJob: Type of provided parameters does not match expected");
    public static final TaskSchedulerException ErrOneTimeJobStartDateTimePast = new TaskSchedulerException("task_scheduler: OneTimeJob: start must not be in the past");
    public static final TaskSchedulerException ErrStopExecutorTimedOut = new TaskSchedulerException("task_scheduler: timed out waiting for executor to stop");
    public static final TaskSchedulerException ErrStopJobsTimedOut = new TaskSchedulerException("task_scheduler: timed out waiting for jobs to finish");
    public static final TaskSchedulerException ErrStopSchedulerTimedOut = new TaskSchedulerException("task_scheduler: timed out waiting for scheduler to stop");
    public static final TaskSchedulerException ErrWeeklyJobAtTimeNil = new TaskSchedulerException("task_scheduler: WeeklyJob: atTime within atTimes must not be nil");
    public static final TaskSchedulerException ErrWeeklyJobAtTimesNil = new TaskSchedulerException("task_scheduler: WeeklyJob: atTimes must not be nil");
    public static final TaskSchedulerException ErrWeeklyJobDaysOfTheWeekNil = new TaskSchedulerException("task_scheduler: WeeklyJob: daysOfTheWeek must not be nil");
    public static final TaskSchedulerException ErrWeeklyJobHours = new TaskSchedulerException("task_scheduler: WeeklyJob: atTimes hours must be between 0 and 23 inclusive");
    public static final TaskSchedulerException ErrWeeklyJobMinutesSeconds = new TaskSchedulerException("task_scheduler: WeeklyJob: atTimes minutes and seconds must be between 0 and 59 inclusive");
    public static final TaskSchedulerException ErrWithClockNil = new TaskSchedulerException("task_scheduler: WithClock: clock must not be nil");
    public static final TaskSchedulerException ErrWithDistributedElectorNil = new TaskSchedulerException("task_scheduler: WithDistributedElector: elector must not be nil");
    public static final TaskSchedulerException ErrWithDistributedLockerNil = new TaskSchedulerException("task_scheduler: WithDistributedLocker: locker must not be nil");
    public static final TaskSchedulerException ErrWithDistributedJobLockerNil = new TaskSchedulerException("task_scheduler: WithDistributedJobLocker: locker must not be nil");
    public static final TaskSchedulerException ErrWithLimitConcurrentJobsZero = new TaskSchedulerException("task_scheduler: WithLimitConcurrentJobs: limit must be greater than 0");
    public static final TaskSchedulerException ErrWithLocationNil = new TaskSchedulerException("task_scheduler: WithLocation: location must not be nil");
    public static final TaskSchedulerException ErrWithLoggerNil = new TaskSchedulerException("task_scheduler: WithLogger: logger must not be nil");
    public static final TaskSchedulerException ErrWithMonitorNil = new TaskSchedulerException("task_scheduler: WithMonitor: monitor must not be nil");
    public static final TaskSchedulerException ErrWithNameEmpty = new TaskSchedulerException("task_scheduler: WithName: name must not be empty");
    public static final TaskSchedulerException ErrWithStartDateTimePast = new TaskSchedulerException("task_scheduler: WithStartDateTime: start must not be in the past");
    public static final TaskSchedulerException ErrWithStopTimeoutZeroOrNegative = new TaskSchedulerException("task_scheduler: WithStopTimeout: timeout must be greater than 0");
    public static final TaskSchedulerException ErrNone = new TaskSchedulerException("task_scheduler: None");

    // Internal errors
    public static final TaskSchedulerException errAtTimeNil = new TaskSchedulerException("errAtTimeNil");
    public static final TaskSchedulerException errAtTimesNil = new TaskSchedulerException("errAtTimesNil");
    public static final TaskSchedulerException errAtTimeHours = new TaskSchedulerException("errAtTimeHours");
    public static final TaskSchedulerException errAtTimeMinSec = new TaskSchedulerException("errAtTimeMinSec");
}
