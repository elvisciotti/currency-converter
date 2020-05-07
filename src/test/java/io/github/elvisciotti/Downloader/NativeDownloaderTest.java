package io.github.elvisciotti.Downloader;

import io.github.elvisciotti.ResourceReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NativeDownloaderTest {
    ResourceReader resourceReader = new ResourceReader();

    @Test
    void getContentReturnsSuccess() throws IOException {
        String content = new NativeDownloader().getContent(new URL("file://" + resourceReader.getXmlFilePath()));
        assertEquals(content, resourceReader.getXmlContent());
    }

    @Test
    void getContentNotFound() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            new NativeDownloader().getContent(new URL("file://" + resourceReader.getXmlFilePath() + "NOT_FOUND"));
        });
    }
}