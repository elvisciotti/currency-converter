package io.github.elvisciotti.Downloader;

import io.github.elvisciotti.Cache.MemoryCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DownloaderCachedTest {
    private final String mockContent = "mock Content";
    private DownloaderInterface innerDownloaderMock;
    private DownloaderCached sut = new DownloaderCached(innerDownloaderMock, new MemoryCache());
    private URL url;

    @BeforeEach
    void setUp() throws MalformedURLException {
        innerDownloaderMock = mock(DownloaderInterface.class);
        sut = new DownloaderCached(innerDownloaderMock, new MemoryCache());
        url = new URL("http://www.example.org");
    }

    @Test
    void getContentOnlyCalledOnceOnInteralDownloader() throws IOException {
        when(innerDownloaderMock.getContent(url))
                .thenReturn(mockContent);

        for (int i = 0; i < 3; i++) {
            assertEquals(mockContent, sut.getContent(url));
        }

        verify(innerDownloaderMock, times(1)).getContent(url);
    }
}