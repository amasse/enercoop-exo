package org.enercoop.exo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Read a single file to transform the content to an URL list
 */
public class FileUrlProvider implements UrlProvider {

    private final Path file;

    FileUrlProvider(Path file) {
        this.file = file;
    }

    @Override
    public List<URL> provide() {
        try {
            List<String> lines = Files.readAllLines(file);
            return lines.stream().map(s -> {
                try {
                    return new URL(s);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
