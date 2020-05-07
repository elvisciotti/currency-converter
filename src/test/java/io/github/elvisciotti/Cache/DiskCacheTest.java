package io.github.elvisciotti.Cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DiskCacheTest {
    final private String systemTmpDir = System.getProperty("java.io.tmpdir");
    final private String filesPrefix = "io.github.elvisciotti.cacheconverter-junit";
    final private String cacheId = "cacheId";
    private CacheInterface sut;
    private String cacheContentMockUTF8 = "newvàlue! ";

    @Test
    void itemsStoredAndRetrievedCorrectlyWithPositiveLifetime() throws IOException {
        sut = new DiskCache(systemTmpDir, filesPrefix, 3600);

        sut.clean(cacheId);
        assertFalse(sut.exists(cacheId));
        assertNull(sut.get(cacheId));
        sut.store(cacheId, "oldvalue");
        cacheContentMockUTF8 = "newvàlue! ";
        sut.store(cacheId, cacheContentMockUTF8);
        assertTrue(sut.exists(cacheId));
        assertEquals(cacheContentMockUTF8, sut.get(cacheId));
    }

    @Test
    void itemsStoredButNotRetrievedWithZeroLifetime() throws IOException {
        sut = new DiskCache(systemTmpDir, filesPrefix, 0);

        sut.clean(cacheId);
        assertFalse(sut.exists(cacheId));
        sut.store(cacheId, cacheContentMockUTF8);
        assertFalse(sut.exists(cacheId));
        assertNull(sut.get(cacheId));
    }
}