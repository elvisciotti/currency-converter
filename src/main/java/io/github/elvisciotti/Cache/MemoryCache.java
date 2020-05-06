package io.github.elvisciotti.Cache;

import java.io.IOException;
import java.util.HashMap;

public class MemoryCache implements CacheInterface {
    private static HashMap<String, String> cache = new HashMap<>();

    @Override
    public boolean exists(String key) {
        return cache.get(key) != null;
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public void store(String key, String data) {
        cache.put(key, data);
    }

    @Override
    public void clean(String key) throws IOException {
        cache.remove(key);
    }
}
