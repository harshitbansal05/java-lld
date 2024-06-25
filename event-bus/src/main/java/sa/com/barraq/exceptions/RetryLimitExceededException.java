package sa.com.barraq.exceptions;

public class RetryLimitExceededException extends RuntimeException {
    public RetryLimitExceededException(Throwable cause) {
        super(cause);
    }
}
