package io.github.panther.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface ICache<S, U, V> {

    void set(S key, V value, long timeout, TimeUnit unit);

    V get(S key);

    void setHash(S key, U hashKey, V value, long timeout, TimeUnit unit);

    V getHash(S key, U hashKey);

    void setHashAll(S key, Map<U, V> map, long timeout, TimeUnit unit);

    Map<U, V> getHashAll(S key);

    void deleteHash(S key);

    void setHashAll(S key, Map<U, V> map);
}
