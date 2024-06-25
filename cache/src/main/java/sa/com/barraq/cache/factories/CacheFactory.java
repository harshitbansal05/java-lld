package sa.com.barraq.cache.factories;

import sa.com.barraq.cache.Cache;
import sa.com.barraq.cache.policies.LRUEvictionPolicy;
import sa.com.barraq.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<>(new LRUEvictionPolicy<>(), new HashMapBasedStorage<>(capacity));
    }
}
