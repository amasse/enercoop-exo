package org.enercoop.exo;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectoryUrlProviderTest {

    @Test
    public void should_read_url_from_log_files() throws URISyntaxException {
        // Given
        Path path = Path.of("./src/test/resources/logs");
        DirectoryUrlProvider provider = new DirectoryUrlProvider(path);

        // When
        List<URL> urlList = provider.provide();

        // Then
        assertThat(urlList).hasSize(72000);
    }
}