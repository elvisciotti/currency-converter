package io.github.elvisciotti.Cache;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

public class DiskCache implements CacheInterface {

    private String rootPath;
    private String filesPrefix;
    private int expireSeconds;

    public DiskCache(String rootPath, String filesPrefix, int expireSeconds) {
        this.rootPath = rootPath.trim();
        if (this.rootPath.charAt(this.rootPath.length() - 1) == '/') {
            this.rootPath += File.pathSeparator;
        }
        this.filesPrefix = filesPrefix;
        this.expireSeconds = expireSeconds;
    }

    @Override
    public boolean exists(String key) throws IOException {
        Path path = getPathFromKey(key);
        if (!Files.exists(path)) {
            return false;
        }
        LocalDateTime cacheValidIfAfter = LocalDateTime.now().minusSeconds(expireSeconds);
        FileTime fileTime = Files.getLastModifiedTime(path);
        LocalDateTime convertedFileTime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());

        return convertedFileTime.isAfter(cacheValidIfAfter);
    }

    @Override
    public String get(String key) throws IOException {
        if (!exists(key)) {
            return null;
        }

        return Files
                .newBufferedReader(getPathFromKey(key))
                .lines()
                .collect(Collectors.joining());
    }


    @Override
    public void store(String key, String data) throws IOException {
        File to = getPathFromKey(key).toFile();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to), StandardCharsets.UTF_8))) {
            writer.write(data);
        }
    }

    private Path getPathFromKey(String key) {
        return Paths.get(this.rootPath + this.filesPrefix + key);
    }

    @Override
    public void clean(String key) throws IOException {
        Files.deleteIfExists(getPathFromKey(key));
    }
}
