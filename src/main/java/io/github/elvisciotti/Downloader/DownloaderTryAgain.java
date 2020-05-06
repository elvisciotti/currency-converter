package io.github.elvisciotti.Downloader;

import java.io.IOException;
import java.net.URL;

public class DownloaderTryAgain implements DownloaderInterface {
    private DownloaderInterface innerDownloader;
    private int maxAttempts;

    public DownloaderTryAgain(DownloaderInterface innerDownloader, int maxAttempts) {
        this.innerDownloader = innerDownloader;
        this.maxAttempts = maxAttempts;
    }

    public DownloaderTryAgain(DownloaderInterface innerDownloader) {
        this(innerDownloader, 3);
    }

    public String getContent(URL url) throws IOException {
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                return this.innerDownloader.getContent(url);
            } catch (IOException e) {
                errorMessage.append("Failed to read URL at attempt #" + i + " (" + e.getMessage() + "); ");
            }
        }

        throw new IOException(errorMessage.toString());
    }
}
