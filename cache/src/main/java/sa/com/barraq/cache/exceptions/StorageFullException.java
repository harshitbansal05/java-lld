package sa.com.barraq.cache.exceptions;

public class StorageFullException extends RuntimeException {

    public StorageFullException(String message) {
        super(message);
    }
}
