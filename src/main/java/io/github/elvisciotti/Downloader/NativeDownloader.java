package io.github.elvisciotti.Downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NativeDownloader implements DownloaderInterface {

    //TODO evaluate implementing AutoCloseable

    @Override
    public String getContent(URL url) throws IOException {
        try (
                InputStreamReader isr = new InputStreamReader(url.openStream());
                BufferedReader br = new BufferedReader(isr);
        ) {
            StringBuffer content = new StringBuffer();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                content.append(inputLine);
            }

            return content.toString();
        }
    }
}
