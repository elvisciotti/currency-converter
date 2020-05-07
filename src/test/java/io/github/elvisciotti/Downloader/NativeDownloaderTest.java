package io.github.elvisciotti.Downloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NativeDownloaderTest {
    private String xmlContent1;
    private String filePath;

    {
        try {
            filePath = getClass()
                    .getClassLoader()
                    .getResource("junit-fixtures/ecb.xml")
                    .getFile();

            xmlContent1 = Files
                    .newBufferedReader(Paths.get(filePath))
                    .lines()
                    .collect(Collectors.joining());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getContentReturnsSuccess() throws IOException {
        String content = new NativeDownloader().getContent(new URL("file://" + filePath));
        assertEquals(content, xmlContent1);
    }

    @Test
    void getContentNotFound() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            new NativeDownloader().getContent(new URL("file://" + filePath + "NOT_FOUND"));
        });
    }
}