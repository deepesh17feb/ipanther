package io.github.panther.cache.impl;

import io.github.panther.cache.ICache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("panther_redis")
@ConditionalOnProperty(name = "panther.cache.store", havingValue = "panther_redis")
public class RedisCache implements ICache<String, String, Object> {

    @Autowired
    @Qualifier("pantherRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    @Override
    public void setHash(String key, String hashKey, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void setHashAll(String key, Map<String, Object> map, long timeout, TimeUnit unit) {
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Map<String, Object> getHashAll(String key) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    @Override
    public void deleteHash(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setHashAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
}
