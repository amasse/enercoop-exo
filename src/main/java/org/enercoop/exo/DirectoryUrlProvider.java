package org.enercoop.exo;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryUrlProvider implements UrlProvider {

    private final Path directory;

    private List<URL> content;

    DirectoryUrlProvider(Path directory) {
        this.directory = directory;
    }

    @Override
    public List<URL> provide() {
        if (content == null) {
            try {
                content = new ArrayList<>();
                Files.walk(directory).filter(path -> path.toFile().isFile())
                        .forEach(path -> content.addAll(new FileUrlProvider(path).provide()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return content;
    }
}
