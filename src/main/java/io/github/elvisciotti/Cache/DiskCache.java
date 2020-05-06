package io.github.elvisciotti.Cache;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiskCache implements CacheInterface {

    private String rootPath;
    private String filesPrefix;

    public DiskCache(String rootPath, String filesPrefix) {
        this.rootPath = rootPath.trim();
        if (this.rootPath.charAt(this.rootPath.length() - 1) == '/') {
            this.rootPath += File.pathSeparator;
        }
        this.filesPrefix = filesPrefix;
    }

    @Override
    public boolean exists(String key) {
        return Files.exists(getPathFromKey(key));
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
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to), "UTF-8"))) {
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
