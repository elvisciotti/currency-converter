package io.github.elvisciotti.Cache;

import java.io.IOException;

public class VoidCache implements CacheInterface {
    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void store(String key, String data) {
    }

    @Override
    public void clean(String key) throws IOException {

    }
}
