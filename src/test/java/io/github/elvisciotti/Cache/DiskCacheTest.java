package io.github.elvisciotti.Cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DiskCacheTest {
    final private String systemTmpDir = System.getProperty("java.io.tmpdir");
    final private String filesPrefix = "io.github.elvisciotti.cacheconverter-junit";
    final private String cacheId = "cacheId";
    private CacheInterface sut = new DiskCache(systemTmpDir, filesPrefix);

    @BeforeEach
    void setUp() throws IOException {
        sut.clean(cacheId);
    }

    @Test
    void exists() throws IOException {
        assertFalse( sut.exists(cacheId));
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