package io.github.elvisciotti.Cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MemoryCacheTest {
    final private String cacheId = "cacheId";
    private CacheInterface sut = new MemoryCache();

    @BeforeEach
    void setUp() throws IOException {
        sut.clean(cacheId);
    }

    @Test
    void exists() throws IOException {
        assertFalse(sut.exists(cacheId));
        sut.store(cacheId, "test");
        assertTrue(sut.exists(cacheId));
    }

    @Test
    void getStore() throws IOException {
        assertNull(sut.get(cacheId));
        sut.store(cacheId, "oldvalue");
        String newValue = "newvàlue! ";
        sut.store(cacheId, newValue);
        assertEquals(newValue, sut.get(cacheId));
    }
}