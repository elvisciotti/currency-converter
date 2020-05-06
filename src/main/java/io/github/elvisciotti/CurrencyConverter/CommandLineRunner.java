package io.github.elvisciotti.CurrencyConverter;

import io.github.elvisciotti.Cache.CacheInterface;
import io.github.elvisciotti.Cache.DiskCache;
import io.github.elvisciotti.Cache.MemoryCache;
import io.github.elvisciotti.Downloader.DownloaderCached;
import io.github.elvisciotti.Downloader.DownloaderInterface;
import io.github.elvisciotti.Downloader.DownloaderTryAgain;
import io.github.elvisciotti.Downloader.NativeDownloader;

import java.io.IOException;
import java.net.URL;

public class CommandLineRunner {
    public static void main(String[] args) {
        //TODO move to XML parser
        String rates = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

        CacheInterface cacheMemory = new MemoryCache();
        CacheInterface cacheFile = new DiskCache("/tmp/", "io.github.elvisciotti.cacheconverter");

        DownloaderInterface downloader = new NativeDownloader();
        downloader = new DownloaderTryAgain(downloader);
        downloader = new DownloaderCached(downloader, cacheFile);
        downloader = new DownloaderCached(downloader, cacheMemory);

        try {
            String content = downloader.getContent(new URL(rates));
            System.out.println(content); //TODO replace with args parsing 100 --from=EUR --to=GBP
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
