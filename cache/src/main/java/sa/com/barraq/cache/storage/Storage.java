package sa.com.barraq.cache.storage;

import sa.com.barraq.cache.exceptions.NotFoundException;
import sa.com.barraq.cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {
    public void add(Key key, Value value) throws StorageFullException;

    void remove(Key key) throws NotFoundException;

    Value get(Key key) throws NotFoundException;
}
