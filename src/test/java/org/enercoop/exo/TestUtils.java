package org.enercoop.exo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TestUtils {

    public static UrlProvider createProvider(String... urls) {
        return () ->
                Stream.of(urls).map(s -> {
                    try {
                        return new URL(s);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }
}

