package io.github.elvisciotti.Cache;

import java.io.IOException;

public interface CacheInterface {
    boolean exists(String key) throws IOException;

    String get(String key) throws IOException;

    void store(String key, String data) throws IOException;

    void clean(String key) throws IOException;
}
