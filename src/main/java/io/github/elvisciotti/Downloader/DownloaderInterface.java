package io.github.elvisciotti.Downloader;

import java.io.IOException;
import java.net.URL;

public interface DownloaderInterface {
    String getContent(URL url) throws IOException;
}
