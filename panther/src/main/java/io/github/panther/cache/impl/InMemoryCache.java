package io.github.panther.cache.impl;

import io.github.panther.cache.ICache;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service("panther_in-memory")
public class InMemoryCache implements ICache<String, String, Object> {

    private ConcurrentMap<String, Object> segmentMap = new ConcurrentHashMap<>();

    @Override
    public Object get(String key) {
        return segmentMap.get(key);
    }

    @Override
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        segmentMap.put(key, value);
    }

    @Override
    public void setHash(String key, String hashKey, Object value, long timeout, TimeUnit unit) {
        Map<String, Object> map = new HashMap<>();
        map.put(hashKey, value);

        segmentMap.put(key, map);
    }

    @Override
    public Object getHash(String key, String hashKey) {
        Map<String, Object> map = (Map<String, Object>) segmentMap.get(key);

        return map.get(hashKey);
    }

    @Override
    public void setHashAll(String key, Map<String, Object> valueMap, long timeout, TimeUnit unit) {
        segmentMap.put(key, valueMap);
    }

    @Override
    public Map<String, Object> getHashAll(String key) {
        return (Map<String, Object>) segmentMap.get(key);
    }

    @Override
    public void deleteHash(String key) {
        segmentMap.remove(key);
    }

    @Override
    public void setHashAll(String key, Map<String, Object> map) {

    }
}
