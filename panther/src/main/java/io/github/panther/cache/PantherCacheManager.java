package io.github.panther.cache;

import io.github.panther.cache.model.CacheProvider;
import io.github.panther.config.PantherConfig;
import io.github.panther.constant.PantherConfigParams;
import io.github.panther.exceptions.InvalidCacheStoreException;
import org.springframework.util.StringUtils;

import java.util.Map;

public class PantherCacheManager {

    private String cacheStore;

    private PantherConfig pantherConfig;

    private Map<String, ICache<String, String, Object>> cacheMap;

    public PantherCacheManager(PantherConfig pantherConfig, Map<String, ICache<String, String, Object>> cacheMap) throws InvalidCacheStoreException {
        this.pantherConfig = pantherConfig;
        this.cacheMap = cacheMap;

        init(pantherConfig);
    }

    private void init(PantherConfig pantherConfig) throws InvalidCacheStoreException {
        this.cacheStore = pantherConfig.getProperty(PantherConfigParams.PANTHER_CACHE_STORE);

        if (StringUtils.isEmpty(this.cacheStore)) {
            throw new InvalidCacheStoreException("Unable to find the cache store ::: " + cacheStore);
        }

    }


    public ICache<String, String, Object> fetchCacheStore() throws InvalidCacheStoreException {

        CacheProvider cacheProvider = CacheProvider.fromValue(this.cacheStore);

        switch (cacheProvider) {
            case REDIS:
                if (cacheMap.get(CacheProvider.REDIS.getValue()) != null) {
                    return cacheMap.get(CacheProvider.REDIS.getValue());
                }
                throw new InvalidCacheStoreException(getMessage(cacheProvider));

            case IN_MEMORY:
                if (cacheMap.get(CacheProvider.IN_MEMORY.getValue()) != null) {
                    return cacheMap.get(CacheProvider.IN_MEMORY.getValue());
                }
                throw new InvalidCacheStoreException(getMessage(cacheProvider));

            default:
                throw new InvalidCacheStoreException("Unable to find the cache store ::: " + cacheStore);
        }

    }

    public String getMessage(CacheProvider cacheProviderEnum) {
        return "Unable to find [ " + cacheProviderEnum.getValue() + " ] cache provider";
    }

    public String getCacheStore() {
        return cacheStore;
    }
}
