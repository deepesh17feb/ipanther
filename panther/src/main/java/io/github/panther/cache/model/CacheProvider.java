package io.github.panther.cache.model;

public enum CacheProvider {

    IN_MEMORY("panther_in-memory"),
    REDIS("panther_redis");


    private String value;

    CacheProvider(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CacheProvider fromValue(String text) {
        for (CacheProvider b : CacheProvider.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
