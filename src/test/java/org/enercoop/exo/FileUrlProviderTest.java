package org.enercoop.exo;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUrlProviderTest {

    @Test
    public void should_read_url_from_log_file() throws URISyntaxException {
        Path file = Paths.get(ClassLoader.getSystemResource("test.log").toURI());

        FileUrlProvider provider = new FileUrlProvider(file);

        List<String> list = provider.provide()
                .stream()
                .map(URL::toString)
                .collect(Collectors.toList());

        assertThat(list).containsExactly(
                "https://test.net?a=1&id=772&b=2&c=3",
                "http://a.com?id=164",
                "http://bid.org?a=1&id=430",
                "http://bid.org?a=1&id=274",
                "https://test.net?a=1&id=840&b=2&c=3",
                "https://test.net?a=1&id=940&b=2&c=3","https://test.net?a=1&id=586&b=2&c=3");
    }

}
