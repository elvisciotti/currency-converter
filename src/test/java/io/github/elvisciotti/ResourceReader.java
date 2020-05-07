package io.github.elvisciotti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ResourceReader {
    private String xmlContent;
    private String xmlFilePath;

    public ResourceReader() {
        try {
            xmlFilePath = getClass()
                    .getClassLoader()
                    .getResource("junit-fixtures/ecb.xml")
                    .getFile();

            xmlContent = Files
                    .newBufferedReader(Paths.get(xmlFilePath))
                    .lines()
                    .collect(Collectors.joining());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public String getXmlFilePath() {
        return xmlFilePath;
    }
}
