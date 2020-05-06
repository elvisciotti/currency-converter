package io.github.elvisciotti.Downloader;

import io.github.elvisciotti.Cache.CacheInterface;

import java.io.*;
import java.net.URL;
import java.security.*;

public class DownloaderCached implements DownloaderInterface {
    private DownloaderInterface innerDownloader;
    private CacheInterface cache;

    private static MessageDigest messageDigestSha1 = null;
    static {
        try {
            messageDigestSha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public DownloaderCached(DownloaderInterface innerDownloader, CacheInterface cache) {
        this.innerDownloader = innerDownloader;
        this.cache = cache;
    }

    public String getContent(URL url) throws IOException {
        String cacheId = new String(messageDigestSha1.digest(url.toString().getBytes()));
        if (this.cache.exists(cacheId)) {
            String cachedContent = this.cache.get(cacheId);
            if (cachedContent.length() > 0) {
                return cachedContent;
            }
        }

        String freshContent = innerDownloader.getContent(url);
        this.cache.store(cacheId, freshContent);

        return freshContent;
    }
}
