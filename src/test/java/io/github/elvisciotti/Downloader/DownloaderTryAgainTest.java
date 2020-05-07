package io.github.elvisciotti.Downloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DownloaderTryAgainTest {
    private final String mockContent = "mock Content";
    private DownloaderTryAgain sut;
    private DownloaderInterface innerDownloaderMock;
    private URL url;

    @BeforeEach
    void setUp() throws MalformedURLException {
        innerDownloaderMock = mock(DownloaderInterface.class);
        sut = new DownloaderTryAgain(innerDownloaderMock);
        url = new URL("http://www.example.org");
    }

    @Test
    void getContentReturnsContentAtFirstAttempt() throws IOException {
        when(innerDownloaderMock.getContent(url))
                .thenReturn(mockContent);

        assertEquals(mockContent, sut.getContent(url));
    }

    @Test
    void getContentAlwaysFail() throws IOException {
        when(innerDownloaderMock.getContent(url))
                .thenThrow(new IOException());

        for (int attempts = 0; attempts < 5; attempts++) {
            Assertions.assertThrows(IOException.class, () -> {
                sut.getContent(url);
            });
        }
    }
}