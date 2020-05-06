package io.github.elvisciotti.Cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VoidCacheTest {
    final private String cacheId = "cacheId";
    private CacheInterface sut = new VoidCache();

    @BeforeEach
    void setUp() throws IOException {

    }

    @Test
    void exists() throws IOException {
        assertFalse(sut.exists(cacheId));
        sut.store(cacheId, "test");
        assertFalse(sut.exists(cacheId));
    }

    @Test
    void getStore() throws IOException {
        assertNull(sut.get(cacheId));
        sut.store(cacheId, "newvàlue! ");
        assertNull(sut.get(cacheId));
    }
}