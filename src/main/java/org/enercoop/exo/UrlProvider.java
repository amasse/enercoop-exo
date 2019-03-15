package org.enercoop.exo;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;


public interface UrlProvider {

    List<URL> provide();

    static UrlProvider getFor(String path) {
        Path target = Path.of(path);
        return target.toFile().isDirectory() ? new DirectoryUrlProvider(target) : new FileUrlProvider(target);
    }

}
